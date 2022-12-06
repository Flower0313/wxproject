package com.holden.wxproject.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName wxproject-DynamicDataSource
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月05日14:31 - 周一
 * @Describe
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType.DataBaseType dataBaseType = DataSourceType.getDataBaseType();
        return dataBaseType;
    }

}

