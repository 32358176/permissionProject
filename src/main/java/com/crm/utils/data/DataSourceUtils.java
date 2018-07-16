package com.crm.utils.data;

import com.crm.utils.TYPE;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceUtils extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> dataSourceKey =
            new InheritableThreadLocal<String>();

    public static void setDataSourceKey(TYPE type) {
//        System.out.println("1-------------->" + type.getType());
        dataSourceKey.set(type.getType());
    }

    @Override
    protected Object determineCurrentLookupKey() {
//        System.out.println("2-------------->" + dataSourceKey.get());
        return dataSourceKey.get();
    }
}
