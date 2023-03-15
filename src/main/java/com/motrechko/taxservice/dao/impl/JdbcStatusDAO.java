package com.motrechko.taxservice.dao.impl;

import com.motrechko.taxservice.dao.ConnectionFactory;
import com.motrechko.taxservice.dao.StatusDAO;
import com.motrechko.taxservice.dao.exception.MySQLException;
import com.motrechko.taxservice.dao.queries.StatusQueries;
import com.motrechko.taxservice.model.Status;
import com.motrechko.taxservice.utils.StatementUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcStatusDAO implements StatusDAO {
    private static final Logger logger = LogManager.getLogger(JdbcStatusDAO.class);
    @Override
    public boolean create(Status status) throws MySQLException {
        if(status == null){throw new MySQLException("Cannot add new Status");}
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            preparedStatement = connection.prepareStatement(StatusQueries.CREATE);
            preparedStatement.setString(1, status.getValue());
            preparedStatement.executeUpdate();
            connection.commit();
            logger.info("Status was created successfully with value: {}",status.getValue());
            return true;
        } catch (SQLException e){
            ConnectionFactory.rollback(connection);
            logger.warn("Failed to insert new status with value: {}" , status.getValue(), e);
            throw new MySQLException("Cannot add new User",e);
        }
        finally {
            if (preparedStatement != null) {
                StatementUtils.close(preparedStatement);
            }
            ConnectionFactory.close(connection);
        }
    }

    @Override
    public void delete(Status status) throws MySQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            statement = connection.prepareStatement(StatusQueries.DELETE_BY_VALUE);
            statement.setString(1, status.getValue());
            int rowsDeleted = statement.executeUpdate();
            connection.commit();
            logger.info("Deleted {} row(s) from the status table", rowsDeleted);
        } catch (SQLException e) {
            ConnectionFactory.rollback(connection);
            logger.warn("failed to delete a status with value: {}", status.getValue(), e);
            throw new MySQLException("Error deleting status from the database", e);
        } finally {
            if (statement != null) {
                StatementUtils.close(statement);
            }
            ConnectionFactory.close(connection);

        }
    }
}
