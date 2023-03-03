package com.motrechko.taxservice.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ConnectionFactoryTest {


    @Test
    public void testGetConnection() throws SQLException {
        // Get a connection with auto-commit status set to true
        Connection connection = ConnectionFactory.getConnection(true);

        // Verify that the connection is not null
        assertNotNull(connection);

        // Verify that the connection has the correct auto-commit status
        assertTrue(connection.getAutoCommit());

        // Close the connection
        ConnectionFactory.close(connection);
    }

    @Test
    public void testRollback() throws SQLException {
        // Mock the database connection
        Connection connection = mock(Connection.class);

        // Call the method being tested
        ConnectionFactory.rollback(connection);

        // Verify that the rollback method was called on the connection
        verify(connection).rollback();
    }

    @Test
    public void testClose() throws SQLException {
        // Mock the database connection
        Connection connection = mock(Connection.class);

        // Call the method being tested
        ConnectionFactory.close(connection);

        // Verify that the close method was called on the connection
        verify(connection).close();
    }


}
