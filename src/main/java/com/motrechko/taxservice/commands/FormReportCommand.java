package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.exception.ReportException;
import com.motrechko.taxservice.mapper.ReportMapper;
import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.enums.Target;
import com.motrechko.taxservice.model.Status;
import com.motrechko.taxservice.service.ReportService;
import com.motrechko.taxservice.service.ReportTypeService;
import jakarta.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FormReportCommand extends FrontCommand {

    private static final Logger logger = LogManager.getLogger(FormReportCommand.class);

    /**
     * Processes the user input from the form and creates a new report.
     *
     * @return CommandResponse that redirects to the Reports command if the report is created successfully,
     *         or an error page if an exception is thrown.
     * @throws ServletException if an error occurs during the servlet execution.
     * @throws IOException if an error occurs during the input/output operations.
     */
    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            Report report = ReportMapper.mapReport(request);
            ReportService reportService = new ReportService();
            reportService.create(report);
            logger.info("New report created for user with ID {}", report.getIdUser());
            return new CommandResponse(Target.COMMAND, "Reports");
        } catch (ReportException e) {
            logger.error("An error occurred while creating a new report: {}", e.getMessage(), e);
            request.getSession().setAttribute("errorMessage", e.getMessage());
            return new CommandResponse(Target.JSP, FrontConstant.ERROR);
        }
    }
}

