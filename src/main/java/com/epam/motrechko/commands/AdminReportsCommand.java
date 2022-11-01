package com.epam.motrechko.commands;

import com.epam.motrechko.FrontConstant;
import com.epam.motrechko.db.dao.AdminDAO;
import com.epam.motrechko.db.dao.DAOFactory;
import com.epam.motrechko.db.entity.AdminReportView;
import com.epam.motrechko.db.entity.User;
import com.epam.motrechko.db.mysql.MySQLException;
import com.epam.motrechko.enums.Target;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.List;

public class AdminReportsCommand extends FrontCommand{
    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            User user = (User) request.getSession(false).getAttribute("currentUser");
            AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
            List<AdminReportView> adminReportViews = adminDAO.getAllUnverifiedReports(user.getId());
            request.getSession(false).setAttribute("adminReportViews",adminReportViews);
            return new CommandResponse(Target.JSP,FrontConstant.REPORTS_ADMIN);

        } catch (MySQLException e) {
            throw new RuntimeException(e);
        }
    }
}
