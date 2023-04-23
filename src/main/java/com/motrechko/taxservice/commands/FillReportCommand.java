package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.exception.ReportException;
import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.enums.Target;
import com.motrechko.taxservice.service.ReportService;
import jakarta.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class FillReportCommand extends FrontCommand {
    private static final Logger logger = LogManager.getLogger(FillReportCommand.class);
    /**
     Retrieves a tax report by ID and forwards to the tax form edit page.
     @return the command response for the tax form edit page
     @throws ServletException if a servlet exception occurs
     @throws IOException if an IO exception occurs
     */
    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            int reportId = Integer.parseInt(request.getParameter("idReport"));
            ReportService reportService = new ReportService();
            Report report = reportService.getReportById(reportId);
            request.setAttribute("report", report);
            logger.info("Filled tax report for editing: {}", reportId);
            return new CommandResponse(Target.JSP, FrontConstant.TAX_FORM_EDIT);
        } catch (ReportException e) {
            logger.error("Error filling tax report for editing", e);
            request.getSession().setAttribute("errorMessage", e.getMessage());
            return new CommandResponse(Target.JSP, FrontConstant.ERROR);
        }
    }
}
