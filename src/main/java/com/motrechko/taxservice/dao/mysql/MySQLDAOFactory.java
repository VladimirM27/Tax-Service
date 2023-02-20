package com.motrechko.taxservice.dao.mysql;

import com.motrechko.taxservice.dao.AdminDAO;
import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.ReportDAO;
import com.motrechko.taxservice.dao.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory {

    private UserDAO userDAO;
    private ReportDAO reportDAO;
    private AdminDAO adminDAO;
    @Override
    public void setPooledConnection() {
        connectionPool = MySQLConnectionPool.getInstance();
    }

    @Override
    public ReportDAO getReportDAO() {
        if (reportDAO == null) reportDAO = new MySQLReportDAO();
        return reportDAO;
    }

    @Override
    public AdminDAO getAdminDAO() {
        if (adminDAO == null) adminDAO = new MySQLAdminDAO();
        return adminDAO;
    }

    @Override
    public UserDAO getUserDAO() {
        if (userDAO == null) userDAO = new MySQLUserDAO();
        return userDAO;
    }



    public void rollback(Connection connection) {
        if(connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

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
