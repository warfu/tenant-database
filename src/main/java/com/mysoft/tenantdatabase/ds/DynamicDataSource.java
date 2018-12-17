
package com.mysoft.tenantdatabase.ds;

import com.mysoft.tenantdatabase.dao.*;
import com.mysoft.tenantdatabase.util.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;

import javax.sql.*;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.*;
import org.springframework.util.StringUtils;

@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Autowired
    private ApplicationContext applicationContext;

    @Lazy
    @Autowired
    private DynamicDataSourceSummoner summoner;

    @Lazy
    @Autowired
    private TenantConfigDAO tenantConfigDAO;

    @Override
    protected String determineCurrentLookupKey() {
        String tenantKey = DataSourceContextHolder.getDataSourceKey();
        return DataSourceUtil.getDataSourceBeanKey(tenantKey);
    }

    @Override
    protected DataSource determineTargetDataSource()   {

        String tenantKey = DataSourceContextHolder.getDataSourceKey();
        String beanKey = DataSourceUtil.getDataSourceBeanKey(tenantKey);

        log.info(" beanKey "+beanKey);
        if (!StringUtils.hasText(tenantKey) || applicationContext.containsBean(beanKey)) {
            return super.determineTargetDataSource();
        }
        log.info("tenantKey exist {}",tenantConfigDAO.exist(tenantKey));
        if (tenantConfigDAO.exist(tenantKey)) {
            summoner.registerDynamicDataSources();
        }else {
            throw new IllegalStateException("租户不存在");
        }
        return super.determineTargetDataSource();
    }
}

