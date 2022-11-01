package com.epam.motrechko.commands;

import com.epam.motrechko.FrontConstant;
import com.epam.motrechko.db.dao.DAOFactory;
import com.epam.motrechko.db.dao.ReportDAO;
import com.epam.motrechko.db.entity.Report;
import com.epam.motrechko.db.entity.User;
import com.epam.motrechko.db.mysql.MySQLException;
import com.epam.motrechko.enums.Status;
import com.epam.motrechko.enums.Target;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class AcceptReportCommand extends FrontCommand{
    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            boolean accepted = Boolean.parseBoolean(request.getParameter("accepted"));
            String inspectorComment = request.getParameter("inspectorComment");
            int idReport = Integer.parseInt(request.getParameter("idReport"));
            User user = (User) request.getSession(false).getAttribute("currentUser");

            ReportDAO reportDAO = DAOFactory.getInstance().getReportDAO();

            Report report = reportDAO.getReportById(idReport);
            report.setStatus(accepted ? Status.ACCEPTED : Status.NOT_ACCEPTED);
            report.setIdInspector(user.getId());
            report.setCommentInspector(inspectorComment);
            reportDAO.update(report);
            return new CommandResponse(Target.JSP,FrontConstant.REPORTS_ADMIN);
        } catch (MySQLException e) {
            throw new RuntimeException(e);
        }
    }
}
