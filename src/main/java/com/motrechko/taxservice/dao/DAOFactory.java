package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.dao.impl.*;
import com.motrechko.taxservice.service.ReportTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 A factory for obtaining DAO instances for different entities.
 */
public  class DAOFactory implements IDAOFactory{
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
     */
    public static synchronized DAOFactory getInstance()  {
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
     Returns a new ReportDAO instance.
     @return a new ReportDAO instance
     */
    public ReportDAO getReportDAO() {
        logger.debug("Getting ReportDAO instance");
        ReportTypeService reportTypeService = new ReportTypeService();
        return new JdbcReportDAO(reportTypeService);
    }

    @Override
    public StatusDAO getStatusDAO() {
        return new JdbcStatusDAO();
    }
    @Override
    public InspectorDAO getInspectorDAO() {
        logger.debug("Getting InspectorDAO instance");
        return new JdbcInspectorDAO();
    }

    @Override
    public ReportTypeDAO getReportTypeDAO() {
        logger.debug("Getting ReportTypeDAO instance");
        return new JdbcReportTypeDAO();
    }
}