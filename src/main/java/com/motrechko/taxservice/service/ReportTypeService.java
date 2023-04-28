package com.motrechko.taxservice.service;

import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.ReportTypeDAO;
import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.exception.ReportException;
import com.motrechko.taxservice.model.ReportType;

public class ReportTypeService {
    public ReportType getReportTypeById(int id) throws ReportException, IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        ReportTypeDAO reportTypeDAO = DAOFactory.getInstance().getReportTypeDAO();
        try {
            return reportTypeDAO.getReportTypeById(id);
        } catch (MySQLException e) {
            throw new ReportException("Error retrieving report type: {}" + e.getMessage(), e);
        }
    }

    public ReportType getReportTypeByName(String name) throws ReportException, IllegalArgumentException {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Invalid name");
        }
        ReportTypeDAO reportTypeDAO = DAOFactory.getInstance().getReportTypeDAO();
        try {
            return reportTypeDAO.getReportTypeByName(name);
        } catch (MySQLException e) {
            throw new ReportException("Error retrieving report type: {}" + e.getMessage(), e);
        }
    }
}
