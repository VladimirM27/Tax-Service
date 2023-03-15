package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.model.UnverifiedReportsView;
import com.motrechko.taxservice.dao.exception.MySQLException;
import com.motrechko.taxservice.enums.Target;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class UnverifiedReportsCommand extends FrontCommand{
    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            //AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
            int idReport = Integer.parseInt(request.getParameter("idReport"));
           // UnverifiedReportsView unverifiedReportsView = adminDAO.getUnverifiedReports(idReport);
           // request.getSession(false).setAttribute("UnverifiedReportsView", unverifiedReportsView);
            return new CommandResponse(Target.JSP,FrontConstant.REPORT_VERIFICATION);
        } catch (Exception e) {
            //todo MySQL exp
            throw new RuntimeException(e);
        }
    }
}
