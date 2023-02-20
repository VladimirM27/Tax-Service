package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.dao.impl.JdbcAdminDAO;
import com.motrechko.taxservice.dao.impl.JdbcReportDAO;
import com.motrechko.taxservice.dao.impl.JdbcUserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;

/**
 A factory for obtaining DAO instances for different entities.
 */
public class DAOFactory {
    private static final Logger logger = LogManager.getLogger(DAOFactory.class);
    private static DAOFactory instance;
    /**
     Private constructor to ensure that instances are only created via the getInstance() method.*/
    private DAOFactory(){
        logger.info("Created new DAOFactory instance");
    }
    /**
     Returns the singleton instance of the DAOFactory class.
     If an instance has not been created yet, creates a new one.
     @return the singleton instance of DAOFactory
     @throws SQLException if a database access error occurs
     */
    public static synchronized DAOFactory getInstance() throws SQLException {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }
    /**
     Returns a new UserDAO instance.
     @return a new UserDAO instance
     */
    public UserDAO getUserDAO() {
        logger.debug("Getting UserDAO instance");
        return new JdbcUserDAO();
    }
    /**
     Returns a new AdminDAO instance.
     @return a new AdminDAO instance
     */
    public AdminDAO getAdminDAO() {
        logger.debug("Getting AdminDAO instance");
        return new JdbcAdminDAO();
    }
    /**
     Returns a new ReportDAO instance.
     @return a new ReportDAO instance
     */
    public ReportDAO getReportDAO() {
        logger.debug("Getting ReportDAO instance");
        return new JdbcReportDAO();
    }
}