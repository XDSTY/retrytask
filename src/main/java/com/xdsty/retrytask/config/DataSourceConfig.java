package com.xdsty.retrytask.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.my.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Autowired
    public Connection getConnection(DataSource dataSource) throws SQLException {
        return dataSource.getConnection();
    }

}
