package com.holden.wxproject.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName wxproject-DataSourceConfig
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年6月29日16:43 - 周四
 * @Describe
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = BaseConstant.SPIDER)
    @ConfigurationProperties(prefix = "spring.datasource.spider")
    public DataSource spider() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = BaseConstant.PHOENIX)
    @ConfigurationProperties(prefix = "spring.datasource.phoenix")
    public DataSource phoenix() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource(@Qualifier(BaseConstant.SPIDER) DataSource spider, @Qualifier(BaseConstant.PHOENIX) DataSource phoenix) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(spider);
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put(BaseConstant.SPIDER, spider);
        dsMap.put(BaseConstant.PHOENIX, phoenix);

        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    /**
     * 配置@Transactional注解,用来管理事务的
     * Autowired
     * private PlatformTransactionManager transactionManager;
     *
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }


}
