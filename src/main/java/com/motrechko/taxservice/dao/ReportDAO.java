package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.model.*;
import com.motrechko.taxservice.exception.MySQLException;

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
     Updates the user  report associated with an existing report in the database.
     @param report the report to be updated
     @throws MySQLException if a SQL exception occurs during the update process
     */
    void update(Report report) throws MySQLException;
    /**
     Retrieves a report by its ID from the database.
     @param reportID the ID of the report to be retrieved
     @return the report with the given ID, or null if no such report exists
     @throws MySQLException if a SQL exception occurs during the retrieval process
     */
    Report getReportById(int reportID) throws MySQLException;

    /**
     * Retrieves a list of reports belonging to a specific Inspector from the database.
     * @param idInspector the ID of the Inspector
     * @return a list of reports belonging to the specified inspector
     * @throws MySQLException if a SQL exception occurs during the retrieval process
     */
    List<Report> getReportsByInspector(int idInspector) throws  MySQLException;
    /**
     Retrieves a list of reports view belonging to a specific user from the database.
     @param userId the ID of the user whose reports are to be retrieved
     @return a list of reports view belonging to the specified user
     @throws MySQLException if a SQL exception occurs during the retrieval process
     */
    List<ReportView> getReportViewByUserId(int userId, int startIndex, int endIndex) throws MySQLException;
    /**
     Deletes a report from the database.
     @throws MySQLException if a SQL exception occurs during the deletion process
     */
    void delete(int idReport) throws MySQLException;

    /**
     Retrieves all unverified reports for a given inspector.
     @param status the status of the reports
     @return a list of AdminReportView objects representing the unverified reports
     @throws MySQLException if a database access error occurs
     */
    List<AdminReportView> getAllUnverifiedReports(int idInspector, Status status, int page,int recordsPerPage) throws MySQLException;
    /**
     Gets an unverified report for this user.
     @param idUser the id of the user whose unverified report to retrieve
     @return an UnverifiedReportsView object representing the unverified report for the user
     @throws MySQLException if a database access error occurs
     */
    UnverifiedReportsView getUnverifiedReports(int idUser) throws MySQLException;

    int countReportsByUser(int idUser) throws MySQLException;
    int countReportsByStatus(Status status) throws MySQLException;
    int countUnverifiedReportByInspectorAndStatus(int idInspector, Status status) throws MySQLException;


}
