package com.motrechko.taxservice.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class PoolConfigTest {
    /**
     A mock DatabaseConfig object used for testing.
     */
    @Mock
    private DatabaseConfig mockDbConfig;
    /**
     The PoolConfig object being tested.
     */
    private PoolConfig poolConfig;
    /**
     Sets up the test environment before each test by creating a mock DatabaseConfig object and initializing the PoolConfig object with it.
     */
    @BeforeEach
    public void setUp()  {
        MockitoAnnotations.openMocks(this);
        when(mockDbConfig.getUrl()).thenReturn("jdbc:mysql://localhost:3306/mydb");
        when(mockDbConfig.getDriver()).thenReturn("com.mysql.jdbc.Driver");
        when(mockDbConfig.getUsername()).thenReturn("root");
        when(mockDbConfig.getPassword()).thenReturn("password");
        poolConfig = new PoolConfig(mockDbConfig);
    }
    /**
     Tests the getDataSource() method of the PoolConfig class by asserting that the returned BasicDataSource object has the expected properties.
     */
    @Test
    public void testGetDataSource() {
        BasicDataSource dataSource = poolConfig.getDataSource();
        assertEquals("jdbc:mysql://localhost:3306/mydb", dataSource.getUrl());
        assertEquals("com.mysql.jdbc.Driver", dataSource.getDriverClassName());
        assertEquals("root", dataSource.getUsername());
        assertEquals("password", dataSource.getPassword());
        assertEquals(5, dataSource.getInitialSize());
        assertEquals(10, dataSource.getMaxTotal());
        assertEquals(5, dataSource.getMaxIdle());
        assertEquals(2, dataSource.getMinIdle());
        assertEquals(10000, dataSource.getMaxWaitMillis());
    }
}
