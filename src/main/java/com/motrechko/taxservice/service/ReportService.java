package com.motrechko.taxservice.service;

import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.ReportDAO;
import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.exception.ReportException;
import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.model.ReportView;

import java.util.List;

public class ReportService {

    private final ReportDAO reportDAO;

    public ReportService(){
        reportDAO = DAOFactory.getInstance().getReportDAO();
    }
    /**
     * Retrieves a list of reports for the given user.
     *
     * @param idUser the ID of the user to retrieve reports for
     * @return a list of ReportView objects for the given user
     * @throws ReportException if an error occurs while retrieving the reports
     */
    public List<ReportView> getUserReports(int idUser) throws ReportException {
        if (idUser <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        try {
            return reportDAO.getReportViewByUserId(idUser);
        } catch (MySQLException e) {
            throw new ReportException("Error retrieving user reports: {}" + e.getMessage(), e);
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
        if (idReport <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        try {
            return reportDAO.getReportById(idReport);
        } catch (MySQLException e) {
            throw new ReportException("Error retrieving user reports: {}" + e.getMessage(), e);
        }
    }

    public boolean create(Report report) throws ReportException {
        if(report == null)
            throw  new IllegalArgumentException("report cannot be null");
        try {
            return reportDAO.create(report);
        } catch (MySQLException e) {
            throw new ReportException("Error adding user report: {}" + e.getMessage(), e);
        }
    }
}

