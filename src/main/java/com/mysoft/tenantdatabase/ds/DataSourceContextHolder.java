package com.mysoft.tenantdatabase.ds;

public class DataSourceContextHolder {

    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<>();

    public static void setDataSourceKey(String tenantKey) {
        dataSourceKey.set(tenantKey);
    }

    public static String getDataSourceKey() {
        return dataSourceKey.get();
    }

    public static void clearDataSourceKey() {
        dataSourceKey.remove();
    }
}
