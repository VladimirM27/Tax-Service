package com.epam.motrechko.commands;

import com.epam.motrechko.FrontConstant;
import com.epam.motrechko.db.dao.AdminDAO;
import com.epam.motrechko.db.dao.DAOFactory;
import com.epam.motrechko.db.entity.AdminReportView;
import com.epam.motrechko.db.mysql.MySQLException;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.List;

public class AdminReportsCommand extends FrontCommand{
    @Override
    public String process() throws ServletException, IOException {
        try {
            AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
            List<AdminReportView> adminReportViews = adminDAO.getAllUnverifiedReports();
            request.getSession(false).setAttribute("adminReportViews",adminReportViews);
            return FrontConstant.REPORTS_ADMIN;
        } catch (MySQLException e) {
            throw new RuntimeException(e);
        }
    }
}
