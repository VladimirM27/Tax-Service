package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.config.DatabaseConfig;
import com.motrechko.taxservice.config.PoolConfig;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * A factory for obtaining database connections from a connection pool.
 */
public class ConnectionFactory {

    // Initialize the pool configuration using the database properties.
    private final static PoolConfig poolConfig = new PoolConfig(new DatabaseConfig());

    /**
     * Obtains a database connection from the connection pool.
     *
     * @return a database connection
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return poolConfig.getDataSource().getConnection();
    }
}
