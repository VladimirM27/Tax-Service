package com.motrechko.taxservice.dao;

/**
 An interface representing a factory for obtaining DAO instances for different entities.
 */
public interface IDAOFactory {
    /**
     Returns a new instance of UserDAO.
     @return a new instance of UserDAO.
     */
    UserDAO getUserDAO();
    /**
     Returns a new instance of AdminDAO.
     @return a new instance of AdminDAO.
     */
    InspectorDAO getInspectorDAO();
    /**
     Returns a new instance of ReportDAO.
     @return a new instance of ReportDAO.
     */
    ReportDAO getReportDAO();
}
