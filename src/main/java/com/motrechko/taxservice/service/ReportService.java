package com.motrechko.taxservice.service;

import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.ReportDAO;
import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.exception.ReportException;
import com.motrechko.taxservice.model.ReportView;

import java.util.List;

public class ReportService {

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
        ReportDAO reportDAO = DAOFactory.getInstance().getReportDAO();
        try {
            return reportDAO.getReportViewByUserId(idUser);
        } catch (MySQLException e) {
            throw new ReportException("Error retrieving user reports: {}" + e.getMessage(), e);
        }
    }
}

