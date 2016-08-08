package org.hsweb.generator.db;


import org.hsweb.ezorm.meta.DatabaseMetaData;
import org.hsweb.ezorm.render.dialect.MysqlDatabaseMeta;
import org.hsweb.ezorm.render.dialect.OracleDatabaseMeta;
import org.hsweb.ezorm.run.Database;
import org.hsweb.ezorm.run.simple.SimpleDatabase;

import java.util.Properties;

/**
 * 数据库工厂，用于创建进行数据库操作的对象.目前只支持mysql和oracle数据库.
 */
public class DatabaseFactory {

    private DatabaseFactory() {
    }

    /**
     * 根据配置创建一个mysql数据库操作对象
     *
     * @param jdbcProperties JDBC配置文件
     * @return mysql数据库操作对象
     */
    public static Database createMysqlDatabase(Properties jdbcProperties) {
        DatabaseMetaData metaData = new MysqlDatabaseMeta();
        return new SimpleDatabase(metaData, new CommonJdbcSqlExecutor(jdbcProperties));
    }

    /**
     * 根据配置创建一个oracle数据库操作对象
     *
     * @param jdbcProperties JDBC配置文件
     * @return oracle数据库操作对象
     */
    public static Database createOracleDatabase(Properties jdbcProperties) {
        DatabaseMetaData metaData = new OracleDatabaseMeta();
        return new SimpleDatabase(metaData, new CommonJdbcSqlExecutor(jdbcProperties));
    }

    public static Database createDatabase(Properties properties) {
        if (String.valueOf(properties.get("jdbc.driver.class")).contains("mysql"))
            return createMysqlDatabase(properties);
        else
            return createOracleDatabase(properties);
    }
}
