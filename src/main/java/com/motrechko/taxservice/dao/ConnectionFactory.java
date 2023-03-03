package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.config.DatabaseConfig;
import com.motrechko.taxservice.config.PoolConfig;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A factory for obtaining database connections from a connection pool.
 */
public class ConnectionFactory  {
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);
    // Initialize the pool configuration using the database properties.
    private final static PoolConfig poolConfig = new PoolConfig(new DatabaseConfig());

    /**
     * Obtains a database connection from the connection pool with auto-commit status set.
     *
     * @param autoCommit specifies the auto-commit status to set
     * @return a database connection with the specified auto-commit status set
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection(boolean autoCommit) throws SQLException {
        Connection connection = poolConfig.getDataSource().getConnection();
        connection.setAutoCommit(autoCommit);
        logger.info("Connection obtained with auto-commit status set to {}", autoCommit);
        return connection;
    }

    /**
     Rolls back the current transaction on the specified database connection.
     If the connection is null or the rollback operation fails, logs an error.
     @param connection the database connection on which to perform the rollback
     */
    public static void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
                logger.info("Transaction rolled back successfully");
            }
        } catch (SQLException e) {
            logger.error("Error occurred while rolling back transaction", e);
        }
    }

    /**
     * Closes the specified connection.
     *
     * @param connection the connection to close
     */
    public static void close(Connection connection)  {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Connection closed");
            } catch (SQLException e) {
                logger.error("Error occurred while closing connection", e);
            }
        }
    }

}
