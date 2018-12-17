package com.mysoft.tenantdatabase.util;

import org.springframework.util.*;

public class DataSourceUtil {

    private static final String DATA_SOURCE_BEAN_KEY_SUFFIX = "_data_source";

    private static final String JDBC_URL_ARGS = "?useUnicode=true&characterEncoding=UTF-8&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";

    private static final String CONNECTION_PROPERTIES = "config.decrypt=true;config.decrypt.key=";
    /**
     * 拼接数据源的spring bean key
     */
    public static String getDataSourceBeanKey(String tenantKey) {
        if (!StringUtils.hasText(tenantKey)) {
            return null;
        }
        return tenantKey + DATA_SOURCE_BEAN_KEY_SUFFIX;
    }
    /**
     * 拼接完整的JDBC URL
     */
    public static String getJDBCUrl(String baseUrl) {
        if (!StringUtils.hasText(baseUrl)) {
            return null;
        }
        return baseUrl + JDBC_URL_ARGS;
    }
    /**
     * 拼接完整的Druid连接属性
     */
    public static String getConnectionProperties(String publicKey) {
        if (!StringUtils.hasText(publicKey)) {
            return null;
        }
        return CONNECTION_PROPERTIES + publicKey;
    }
}
