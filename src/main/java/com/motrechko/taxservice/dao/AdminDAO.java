package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.model.AdminReportView;
import com.motrechko.taxservice.model.UnverifiedReportsView;
import com.motrechko.taxservice.dao.impl.MySQLException;

import java.util.List;

/**
 Interface representing the data access object (DAO) for the Admin entity.
 */
public interface AdminDAO {
    /**
     Retrieves all unverified reports for a given inspector.
     @param inspectorId the id of the inspector whose reports to retrieve
     @return a list of AdminReportView objects representing the unverified reports
     @throws MySQLException if a database access error occurs
     */
    List<AdminReportView> getAllUnverifiedReports(int inspectorId) throws MySQLException;
    /**
     Gets an unverified report for this user.
     @param idUser the id of the user whose unverified report to retrieve
     @return an UnverifiedReportsView object representing the unverified report for the user
     @throws MySQLException if a database access error occurs
     */
    UnverifiedReportsView getUnverifiedReports(int idUser) throws MySQLException;
}