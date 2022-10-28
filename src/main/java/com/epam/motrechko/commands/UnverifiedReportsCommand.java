package com.epam.motrechko.commands;

import com.epam.motrechko.FrontConstant;
import com.epam.motrechko.db.dao.AdminDAO;
import com.epam.motrechko.db.dao.DAOFactory;
import com.epam.motrechko.db.entity.UnverifiedReportsView;
import com.epam.motrechko.db.entity.User;
import com.epam.motrechko.db.mysql.MySQLException;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class UnverifiedReportsCommand extends FrontCommand{
    @Override
    public String process() throws ServletException, IOException {
        try {
            AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
            User user = (User) request.getSession(false).getAttribute("currentUser");
            int idUser = Integer.parseInt(request.getParameter("idUser"));
            UnverifiedReportsView unverifiedReportsView = adminDAO.getUnverifiedReports(idUser);
            request.getSession(false).setAttribute("UnverifiedReportsView", unverifiedReportsView);
            return FrontConstant.REPORT_VERIFICATION;
        } catch (MySQLException e) {
            throw new RuntimeException(e);
        }
    }
}
