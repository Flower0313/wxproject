package com.holden.wxproject.config;

import com.holden.wxproject.util.DataSourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName wxproject-DynamicDataSource
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年6月29日16:57 - 周四
 * @Describe
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceUtil.getDB();
    }
}

