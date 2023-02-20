package com.motrechko.taxservice.config;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Configures a connection pool using the Apache Commons DBCP library.
 */
public class PoolConfig {
    private final BasicDataSource dataSource;

    /**
     * Constructs a new PoolConfig object with the given DatabaseConfig.
     *
     * @param config the database configuration properties
     */
    public PoolConfig(DatabaseConfig config) {
        dataSource = new BasicDataSource();
        dataSource.setUrl(config.getUrl());
        dataSource.setDriverClassName(config.getDriver());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        dataSource.setMaxIdle(5);
        dataSource.setMinIdle(2);
        dataSource.setMaxWaitMillis(10000);
    }

    /**
     * Returns the configured BasicDataSource object, which can be used to obtain connections from the pool.
     *
     * @return the BasicDataSource object
     */
    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
