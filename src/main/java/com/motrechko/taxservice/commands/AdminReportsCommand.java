package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.model.User;
import com.motrechko.taxservice.enums.Target;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class AdminReportsCommand extends FrontCommand{
    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            User user = (User) request.getSession(false).getAttribute("currentUser");
            //AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
           // List<AdminReportView> adminReportViews = adminDAO.getAllUnverifiedReports(user.getId());
          //  request.getSession(false).setAttribute("adminReportViews",adminReportViews);
            return new CommandResponse(Target.JSP,FrontConstant.REPORTS_ADMIN);

        } catch (Exception e) {
            //TODO MySQLExp
            throw new RuntimeException(e);
        }
    }
}
