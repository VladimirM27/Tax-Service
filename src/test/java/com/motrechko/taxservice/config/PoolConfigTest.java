package com.motrechko.taxservice.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PoolConfigTest {
    private DatabaseConfig dbConfig;

    @BeforeEach
    public void setup(){
        dbConfig = new DatabaseConfig();
    }

    @Test
    public void testGetDataSource() {
        PoolConfig poolConfig = new PoolConfig(dbConfig);
        BasicDataSource dataSource = poolConfig.getDataSource();

        Assertions.assertNotNull(dataSource);
        Assertions.assertEquals(dbConfig.getUrl(), dataSource.getUrl());
        Assertions.assertEquals(dbConfig.getDriver(), dataSource.getDriverClassName());
        Assertions.assertEquals(dbConfig.getUsername(), dataSource.getUsername());
        Assertions.assertEquals(dbConfig.getPassword(), dataSource.getPassword());
        Assertions.assertEquals(5, dataSource.getInitialSize());
        Assertions.assertEquals(10, dataSource.getMaxTotal());
        Assertions.assertEquals(5, dataSource.getMaxIdle());
        Assertions.assertEquals(2, dataSource.getMinIdle());
        Assertions.assertEquals(10000, dataSource.getMaxWaitMillis());
    }
}
