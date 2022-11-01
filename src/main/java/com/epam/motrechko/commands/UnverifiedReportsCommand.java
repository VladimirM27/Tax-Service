package com.epam.motrechko.commands;

import com.epam.motrechko.FrontConstant;
import com.epam.motrechko.db.dao.AdminDAO;
import com.epam.motrechko.db.dao.DAOFactory;
import com.epam.motrechko.db.entity.UnverifiedReportsView;
import com.epam.motrechko.db.entity.User;
import com.epam.motrechko.db.mysql.MySQLException;
import com.epam.motrechko.enums.Target;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class UnverifiedReportsCommand extends FrontCommand{
    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
            int idReport = Integer.parseInt(request.getParameter("idReport"));
            UnverifiedReportsView unverifiedReportsView = adminDAO.getUnverifiedReports(idReport);
            request.getSession(false).setAttribute("UnverifiedReportsView", unverifiedReportsView);
            return new CommandResponse(Target.JSP,FrontConstant.REPORT_VERIFICATION);
        } catch (MySQLException e) {
            throw new RuntimeException(e);
        }
    }
}
