package com.xdsty.retrytask.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionUtil {

    private Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);

    @Resource
    private DataSource dataSource;

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
        }
        return null;
    }

}
