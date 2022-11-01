package com.epam.motrechko.commands;

import com.epam.motrechko.FrontConstant;
import com.epam.motrechko.db.dao.DAOFactory;
import com.epam.motrechko.db.dao.ReportDAO;
import com.epam.motrechko.db.entity.ReportView;
import com.epam.motrechko.db.entity.User;
import com.epam.motrechko.db.mysql.MySQLException;
import com.epam.motrechko.enums.Target;
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
