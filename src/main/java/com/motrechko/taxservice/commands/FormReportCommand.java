package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.exception.ReportException;
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
            Report report = mapReport();
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

    /**
     * Maps the user input to a new Report object.
     *
     * @return a new Report object populated with the user input from the form.
     * @throws ReportException if an error occurs while mapping the user input to the Report object.
     */
    private Report mapReport() throws ReportException {
        try {
            ReportTypeService reportTypeService = new ReportTypeService();
            Report report = new Report();
            report.setIdUser(Integer.parseInt(request.getParameter("userId")));
            report.setReportType(reportTypeService.getReportTypeById(Integer.parseInt(request.getParameter("TypeOfReport"))));
            report.setStatus(Status.SUBMITTED);
            report.setCreated(LocalDate.parse(request.getParameter("date")));
            report.setTotalIncome(Double.parseDouble(request.getParameter("total_income")));
            report.setTotalDeductions(Double.parseDouble(request.getParameter("total_deductions")));
            report.setTaxableIncome(Double.parseDouble(request.getParameter("taxable_income")));
            report.setTotalTaxOwned(Double.parseDouble(request.getParameter("total_tax_owned")));
            report.setTotalPaid(Double.parseDouble(request.getParameter("total_paid")));
            report.setCommentUser(request.getParameter("userComment"));
            return report;
        } catch (NumberFormatException | DateTimeParseException e) {
            throw new ReportException("Error while mapping form data to Report object", e);
        }
    }
}

