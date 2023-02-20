package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.ReportDAO;
import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.model.User;
import com.motrechko.taxservice.dao.mysql.MySQLException;
import com.motrechko.taxservice.enums.Status;
import com.motrechko.taxservice.enums.Target;
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
