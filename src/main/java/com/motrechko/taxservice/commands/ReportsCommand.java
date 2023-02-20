package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.ReportDAO;
import com.motrechko.taxservice.model.ReportView;
import com.motrechko.taxservice.model.User;
import com.motrechko.taxservice.dao.mysql.MySQLException;
import com.motrechko.taxservice.enums.Target;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.List;

public class ReportsCommand extends FrontCommand{
    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            ReportDAO reportDAO = DAOFactory.getInstance().getReportDAO();
            User user = (User) request.getSession(false).getAttribute("currentUser");
            List<ReportView> reportViewList = reportDAO.getUserReports(user.getId());
            request.getSession().setAttribute("reportViewList",reportViewList);
            return new CommandResponse(Target.JSP,FrontConstant.REPORTS_USER);

        } catch (MySQLException e) {
           //todo logger
            return new CommandResponse(Target.JSP,FrontConstant.ERROR);

        }
    }
}
