package org.hsweb.generator.db;


import org.hsweb.commons.StringUtils;
import org.hsweb.ezorm.executor.AbstractJdbcSqlExecutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by 浩 on 2016-03-17 0017.
 */
public class CommonJdbcSqlExecutor extends AbstractJdbcSqlExecutor {

    private Properties jdbcProperties;

    public CommonJdbcSqlExecutor(Properties jdbcProperties) {
        this.jdbcProperties = jdbcProperties;
        validProperties();
    }

    protected void validProperties() {
        validProperties("jdbc.driver.class");
        validProperties("jdbc.url");
        validProperties("jdbc.username");
        validProperties("jdbc.password");
        try {
            Class.forName(jdbcProperties.getProperty("jdbc.driver.class"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void validProperties(String key) {
        if (StringUtils.isNullOrEmpty(jdbcProperties.getProperty(key))) {
            throw new RuntimeException(String.format("jdbc配置未找到[%s]配置项", key));
        }
    }

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    jdbcProperties.getProperty("jdbc.url"),
                    jdbcProperties.getProperty("jdbc.username"),
                    jdbcProperties.getProperty("jdbc.password")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void releaseConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
