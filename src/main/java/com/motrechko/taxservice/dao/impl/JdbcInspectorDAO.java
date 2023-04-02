package com.motrechko.taxservice.dao.impl;

import com.motrechko.taxservice.dao.ConnectionFactory;
import com.motrechko.taxservice.dao.InspectorDAO;
import com.motrechko.taxservice.dao.exception.MySQLException;
import com.motrechko.taxservice.dao.queries.InspectorQueries;
import com.motrechko.taxservice.model.Inspector;
import com.motrechko.taxservice.utils.StatementUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class JdbcInspectorDAO implements InspectorDAO {

    private static final Logger logger = LogManager.getLogger(JdbcInspectorDAO.class);
    @Override
    public boolean create(Inspector inspector) throws MySQLException {
            if(inspector == null){throw new MySQLException("Cannot add new Inspector");}
            Connection connection = null;
            try {
                connection = ConnectionFactory.getConnection(false);
                addInspector(connection,inspector);
                connection.commit();
                logger.info("User was created successfully with email: {}", inspector.getEmail());
                return true;
            } catch (SQLException | MySQLException e){
                ConnectionFactory.rollback(connection);
                logger.warn("Failed to insert new user with email: {}" , inspector.getEmail(), e);
                throw new MySQLException("Cannot add new Inspector",e);
            }
            finally {
                ConnectionFactory.close(connection);
            }
    }

    private void addInspector(Connection connection, Inspector inspector) throws MySQLException {
        try (PreparedStatement statement = connection.prepareStatement(InspectorQueries.INSERT_INSPECTOR, Statement.RETURN_GENERATED_KEYS)){
            int i = 0;
            statement.setString(++i, inspector.getFirstName() );
            statement.setString(++i, inspector.getLastName());
            statement.setString(++i, inspector.getEmail());
            statement.setString(++i, inspector.getPassword());
            int c = statement.executeUpdate();
            if(c> 0){
                try(ResultSet set = statement.getGeneratedKeys()){
                    if(set.next()){
                        inspector.setIdInspector(set.getInt(1));
                    }
                }

            }
        } catch (SQLException e){
            logger.warn("Failed to insert new user with email: {}" , inspector.getEmail(), e);
            throw new MySQLException("Cannot insert new inspector" , e);
        }
    }

    @Override
    public Inspector getByEmail(String email) throws MySQLException {
        try (Connection connection = ConnectionFactory.getConnection(true);
             PreparedStatement statement = connection.prepareStatement(InspectorQueries.GET_INSPECTOR_BY_EMAIL)){
            statement.setString(1,email);
            ResultSet set = statement.executeQuery();
            set.next();
            logger.info("a user with the email address {} was found ", email);
            return mapInspector(set);
        } catch (SQLException e){
            logger.warn("Failed to get a user with email: {}" , email, e);
            throw new MySQLException("Cannot get inspector by email", e);
        }
    }

    @Override
    public void delete(int id) throws MySQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            statement = connection.prepareStatement(InspectorQueries.DELETE_BY_ID);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            connection.commit();
            logger.info("Deleted {} row(s) from the users table", rowsDeleted);
        } catch (SQLException e) {
            ConnectionFactory.rollback(connection);
            logger.warn("failed to delete a inspector with ID: {}", id, e);
            throw new MySQLException("Error deleting inspector from the database", e);
        } finally {
            if (statement != null) {
                StatementUtils.close(statement);
            }
            ConnectionFactory.close(connection);

        }
    }

    private Inspector mapInspector(ResultSet set) throws SQLException {
        Inspector inspector = new Inspector();
        inspector.setEmail(set.getString("email"));
        inspector.setFirstName(set.getString("first_name"));
        inspector.setLastName(set.getString("last_name"));
        inspector.setPassword(set.getString("password"));
        inspector.setIdInspector(set.getInt("idinspector"));
        return inspector;
    }
}
