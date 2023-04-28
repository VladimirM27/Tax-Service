package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.exception.ReportException;
import com.motrechko.taxservice.model.*;
import com.motrechko.taxservice.enums.Target;
import com.motrechko.taxservice.service.ReportService;
import com.motrechko.taxservice.utils.SessionUtils;
import jakarta.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AdminReportsCommand extends FrontCommand{
    private static final Logger logger = LogManager.getLogger(AdminReportsCommand.class);
    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            Inspector inspector = SessionUtils.getSessionInspector(request);
            int page = getPageParameter();
            int recordsPerPage = getRecordsPerPageParameter();
            ReportService reportService = new ReportService();
            List<AdminReportView> adminReportViews = reportService.getAllUnverifiedReports(inspector.getId(), Status.SUBMITTED, page, recordsPerPage);
            setSessionAttributes( adminReportViews, page, recordsPerPage, reportService.countAllUnverifiedReports(inspector.getId(), Status.SUBMITTED,page, recordsPerPage));
            request.getSession(false).setAttribute("adminReportViews",adminReportViews);
            return new CommandResponse(Target.JSP,FrontConstant.REPORTS_ADMIN);

        } catch (ReportException | NumberFormatException e) {
            logger.error("Error while adding a report", e);
            SessionUtils.setError(request,"Error while getting all unverified report");
            return new CommandResponse(Target.JSP, FrontConstant.ERROR);
        }
    }
    private int getPageParameter() {
        return Optional.of(Integer.parseInt(request.getParameter("page"))).orElse(1);
    }

    private int getRecordsPerPageParameter() {
        return Optional.of(Integer.parseInt(request.getParameter("recordsPerPage"))).orElse(5);
    }

    private void setSessionAttributes(List<AdminReportView> adminReportViews, int page, int recordsPerPage, int totalPages) {
        request.getSession().setAttribute("reportViewList", adminReportViews);
        request.getSession().setAttribute("page", page);
        request.getSession().setAttribute("recordsPerPage", recordsPerPage);
        request.getSession().setAttribute("totalPages", totalPages);
    }
}
