package com.xdsty.retrytask.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "retry.datasource")
    @Qualifier("retryTaskDataSource")
    public DataSource retryTaskDataSource() {
        return new DruidDataSource();
    }

}
