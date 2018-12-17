
package com.mysoft.tenantdatabase.ds;

import com.alibaba.druid.filter.config.*;
import com.alibaba.druid.pool.*;
import com.google.common.collect.*;
import com.mysoft.tenantdatabase.dao.*;
import com.mysoft.tenantdatabase.entity.*;
import com.mysoft.tenantdatabase.util.*;
import lombok.extern.slf4j.*;

import org.apache.shiro.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.factory.support.*;
import org.springframework.context.*;
import org.springframework.context.event.*;
import org.springframework.stereotype.*;
import java.util.*;

@Slf4j
@Component
public class DynamicDataSourceSummoner implements ApplicationListener<ContextRefreshedEvent>
{

    // 跟spring.xml的默认数据源id保持一致
    private static final String DEFAULT_DATA_SOURCE_BEAN_KEY = "defaultDataSource";

    @Autowired
    private ConfigurableApplicationContext applicationContext;
    @Autowired
    private DynamicDataSource dynamicDataSource;
    @Autowired
    private TenantConfigDAO tenantConfigDAO;

    private static boolean loaded = false;

/**
     * Spring加载完成后执行
     */

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 防止重复执行
        if (!loaded) {
            loaded = true;
            try {
                registerDynamicDataSources();
            } catch (Exception e) {
                log.error("数据源初始化失败, Exception:", e);
            }
        }
    }

   /**
     * 从数据库读取租户的DB配置,并动态注入Spring容器
     */
    public void registerDynamicDataSources() {
        // 获取所有租户的DB配置
        List<TenantConfigEntity> tenantConfigEntities = tenantConfigDAO.listAll();
        if (CollectionUtils.isEmpty(tenantConfigEntities)) {
            throw new IllegalStateException("应用程序初始化失败,请先配置数据源");
        }
        // 把数据源bean注册到容器中
        addDataSourceBeans(tenantConfigEntities);
    }

   /**
     * 根据DataSource创建bean并注册到容器中
     */
    private void addDataSourceBeans(List<TenantConfigEntity> tenantConfigEntities) {
        Map<Object, Object> targetDataSources = Maps.newLinkedHashMap();
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        for (TenantConfigEntity entity : tenantConfigEntities) {
            String beanKey = DataSourceUtil.getDataSourceBeanKey(entity.getTenantKey());
            // 如果该数据源已经在spring里面注册过,则不重新注册
            if (applicationContext.containsBean(beanKey)) {
                DruidDataSource existsDataSource = applicationContext.getBean(beanKey, DruidDataSource.class);
                if (isSameDataSource(existsDataSource, entity)) {
                    continue;
                }
            }
            //  组装bean
            AbstractBeanDefinition beanDefinition = getBeanDefinition(entity, beanKey);
            //  注册bean
            beanFactory.registerBeanDefinition(beanKey, beanDefinition);
            //  放入map中，注意一定是刚才创建bean对象
            targetDataSources.put(beanKey, applicationContext.getBean(beanKey));
        }
        //  将创建的map对象set到 targetDataSources；
        dynamicDataSource.setTargetDataSources(targetDataSources);
        //  必须执行此操作，才会重新初始化AbstractRoutingDataSource 中的 resolvedDataSources，也只有这样，动态切换才会起效
        dynamicDataSource.afterPropertiesSet();
    }

   /**
     * 组装数据源spring bean
     */
    private AbstractBeanDefinition getBeanDefinition(TenantConfigEntity entity, String beanKey) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DruidDataSource.class);
        builder.getBeanDefinition().setAttribute("id", beanKey);
        // 其他配置继承defaultDataSource
        builder.setParentName(DEFAULT_DATA_SOURCE_BEAN_KEY);
        builder.setInitMethodName("init");
        builder.setDestroyMethodName("close");
        builder.addPropertyValue("name", beanKey);
        builder.addPropertyValue("url", DataSourceUtil.getJDBCUrl(entity.getDbUrl()));
        builder.addPropertyValue("username", entity.getDbUser());
        builder.addPropertyValue("password", entity.getDbPassword());
        builder.addPropertyValue("connectionProperties", DataSourceUtil.getConnectionProperties(entity.getDbPublicKey()));
        return builder.getBeanDefinition();
    }

/**
     * 判断Spring容器里面的DataSource与数据库的DataSource信息是否一致
     * 备注:这里没有判断public_key,因为另外三个信息基本可以确定唯一了
     */

    private boolean isSameDataSource(DruidDataSource existsDataSource, TenantConfigEntity entity) {
        boolean sameUrl = Objects.equals(existsDataSource.getUrl(), DataSourceUtil.getJDBCUrl(entity.getDbUrl()));
        if (!sameUrl) {
            return false;
        }
        boolean sameUser = Objects.equals(existsDataSource.getUsername(), entity.getDbUser());
        if (!sameUser) {
            return false;
        }
        try {
            String decryptPassword = ConfigTools.decrypt(entity.getDbPublicKey(), entity.getDbPassword());
            return Objects.equals(existsDataSource.getPassword(), decryptPassword);
        } catch (Exception e) {
            log.error("数据源密码校验失败,Exception:{}", e);
            return false;
        }
    }
}

