package com.epam.motrechko.db.mysql;

import com.epam.motrechko.db.dao.DAOFactory;
import com.epam.motrechko.db.dao.UserDAO;

public class MySQLDAOFactory extends DAOFactory {

    private UserDAO userDAO;
    @Override
    public void setPooledConnection() {
        connectionPool = MySQLConnectionPool.getInstance();
    }

    @Override
    public UserDAO getUserDAO() {
        if (userDAO == null) userDAO = new MySQLUserDAO();
        return userDAO;
    }

    @Override
    public void close(AutoCloseable resource) {
        try {
            resource.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
