package com.motrechko.taxservice.mapper;

import com.motrechko.taxservice.exception.ReportException;
import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.model.Status;
import com.motrechko.taxservice.service.ReportTypeService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ReportMapper {

    private ReportMapper() {
    }

    /**
     * Maps the user input to a new Report object.
     *
     * @return a new Report object populated with the user input from the form.
     * @throws ReportException if an error occurs while mapping the user input to the Report object.
     */
    public static Report mapReport(HttpServletRequest request) throws ReportException {
        try {
            ReportTypeService reportTypeService = new ReportTypeService();
            Report report = new Report();
            String idReportString = request.getParameter("idReport");
            if (!StringUtils.isBlank(idReportString))
                report.setIdReport(Integer.parseInt(idReportString));
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
