package com.motrechko.taxservice.service;

import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.ReportDAO;
import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.exception.ReportException;
import com.motrechko.taxservice.model.AdminReportView;
import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.model.ReportView;
import com.motrechko.taxservice.model.Status;
import com.motrechko.taxservice.pagination.Pagination;

import java.util.List;

public class ReportService {

    private final ReportDAO reportDAO;

    public ReportService() {
        reportDAO = DAOFactory.getInstance().getReportDAO();
    }

    /**
     * Retrieves a list of reports for the given user.
     *
     * @param idUser the ID of the user to retrieve reports for
     * @return a list of ReportView objects for the given user
     * @throws ReportException if an error occurs while retrieving the reports
     */
    public List<ReportView> getUserReports(int idUser,int page, int recordsPerPage) throws ReportException {
        if (idUser < 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        try {
            Pagination pagination = new Pagination(page, recordsPerPage, reportDAO.countReportsByUser(idUser));

            return reportDAO.getReportViewByUserId(idUser, pagination.getStartIndex(), pagination.getEndIndex());
        } catch (MySQLException e) {
            throw new ReportException("Error retrieving user reports: {}" + e.getMessage(), e);
        }
    }


    public int getCountOfPagesReportByUser(int idUser,int page, int recordsPerPage) throws ReportException {
        if (idUser < 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        try {
            Pagination pagination = new Pagination(page, recordsPerPage, reportDAO.countReportsByUser(idUser));
            return pagination.getTotalPages();
        } catch (MySQLException e) {
            throw new ReportException("Error count user reports: {}" + e.getMessage(), e);
        }
    }

    /**
     * Retrieves a report for the given id.
     *
     * @param idReport the ID of the user to retrieve reports for
     * @return a Report for the given user
     * @throws ReportException if an error occurs while retrieving the reports
     */
    public Report getReportById(int idReport) throws ReportException {
        if (idReport < 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        try {
            return reportDAO.getReportById(idReport);
        } catch (MySQLException e) {
            throw new ReportException("Error retrieving user reports: {}" + e.getMessage(), e);
        }
    }

    /**
     * Creates a new report.
     *
     * @param report the report to create
     * @return true if the report was created successfully; otherwise, false
     * @throws ReportException if an error occurs while creating the report
     * @throws IllegalArgumentException if the given report is null
     */
    public boolean create(Report report) throws ReportException {
        if (report == null)
            throw new IllegalArgumentException("report cannot be null");
        try {
            return reportDAO.create(report);
        } catch (MySQLException e) {
            throw new ReportException("Error adding user report: {}" + e.getMessage(), e);
        }
    }

    /**
     * Updates an existing report.
     *
     * @param report the report to update
     * @throws ReportException if an error occurs while updating the report
     * @throws IllegalArgumentException if the given report is null
     */
    public void update(Report report) throws ReportException {
        if (report == null)
            throw new IllegalArgumentException("report cannot be null");
        try {
            reportDAO.update(report);
        } catch (MySQLException e) {
            throw new ReportException("Error while updating user report: {}" + e.getMessage(), e);
        }
    }

    public List<AdminReportView> getAllUnverifiedReports(int idInspector, Status status,int page, int records) throws ReportException {
        if(idInspector < 0 || status == null || page < 0 ){
            throw new IllegalArgumentException("status cannot be null or Invalid idInspector");
        }
        try {
           return reportDAO.getAllUnverifiedReports(idInspector, status,  page, records);
        } catch (MySQLException e) {
            throw new ReportException("Error retrieving all Unverified reports: {}" + e.getMessage(), e);
        }
    }
    public int countAllUnverifiedReports(int idInspector, Status status, int page, int recordsPerPage) throws ReportException {
        if(idInspector < 0 || status == null ){
            throw new IllegalArgumentException("status cannot be null or Invalid idInspector");
        }
        try {
            Pagination pagination = new Pagination(page, recordsPerPage,
                    reportDAO.countUnverifiedReportByInspectorAndStatus(idInspector, status));
            return pagination.getTotalPages();
        } catch (MySQLException e) {
            throw new ReportException("Error retrieving all Unverified reports: {}" + e.getMessage(), e);
        }
    }
}

