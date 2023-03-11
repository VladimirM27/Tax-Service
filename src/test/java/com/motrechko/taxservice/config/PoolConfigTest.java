package com.motrechko.taxservice.config;

import org.junit.jupiter.api.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PoolConfigTest {
    /**
     A mock DatabaseConfig object used for testing.
     */
    @Mock
    private DatabaseConfig mockDbConfig;
    /**
     The PoolConfig object being tested.
     */
    @InjectMocks
    private PoolConfig poolConfig;
    /**
     Sets up the test environment before each test by creating a mock DatabaseConfig object and initializing the PoolConfig object with it.
     */
    @BeforeEach
    public void setUp()  {
        when(this.mockDbConfig.getUrl()).thenReturn("jdbc:mysql://localhost:3306/mydb");
        when(this.mockDbConfig.getDriver()).thenReturn("com.mysql.jdbc.Driver");
        when(this.mockDbConfig.getUsername()).thenReturn("root");
        when(this.mockDbConfig.getPassword()).thenReturn("password");
        poolConfig = new PoolConfig(mockDbConfig);
    }
    /**
     Tests the getDataSource() method of the PoolConfig class by asserting that the returned BasicDataSource object has the expected properties.
     */
    @Test
    public void test_DataSourceConfig() {
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
