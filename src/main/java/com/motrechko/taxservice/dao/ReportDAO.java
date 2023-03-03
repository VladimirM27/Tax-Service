package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.model.ReportView;
import com.motrechko.taxservice.dao.impl.MySQLException;

import java.util.List;


/**
 Interface representing the data access object (DAO) for the Report entity.
 */
public interface ReportDAO {
    /**
     Creates a new report in the database.
     @param report the report to be created
     @return true if the report was created successfully, false otherwise
     @throws MySQLException if a SQL exception occurs during the creation process
     */
    boolean create(Report report) throws MySQLException;
    /**
     Updates an existing report in the database, namely update inspector id, report comment and check status
     @param report the report to be updated
     @throws MySQLException if a SQL exception occurs during the update process
     */
    void updateInspectorInfoInReport(Report report) throws MySQLException;
    /**
     Updates the user  report associated with an existing report in the database.
     @param report the report to be updated
     @throws MySQLException if a SQL exception occurs during the update process
     */
    void updateReport(Report report) throws MySQLException;
    /**
     Deletes a report from the database.
     @throws MySQLException if a SQL exception occurs during the deletion process
     */
    void delete(int id) throws MySQLException;
    /**
     Retrieves a report by its ID from the database.
     @param reportID the ID of the report to be retrieved
     @return the report with the given ID, or null if no such report exists
     @throws MySQLException if a SQL exception occurs during the retrieval process
     */
    Report getReportById(int reportID) throws MySQLException;
    /**
     Retrieves a list of reports belonging to a specific user from the database.
     @param userId the ID of the user whose reports are to be retrieved
     @return a list of reports belonging to the specified user
     @throws MySQLException if a SQL exception occurs during the retrieval process
     */
    List<ReportView> getUserReports(int userId) throws MySQLException;
}
