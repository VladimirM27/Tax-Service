package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.ReportDAO;
import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.dao.exception.MySQLException;
import com.motrechko.taxservice.enums.Target;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateReportCommand extends FrontCommand{
    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            ReportDAO reportDAO = DAOFactory.getInstance().getReportDAO();
            Report report = mapReport();
            reportDAO.update(report);
        } catch (MySQLException e) {
            return new CommandResponse(Target.JSP, FrontConstant.ERROR);
        }
        return new CommandResponse(Target.JSP, FrontConstant.REPORTS_USER);
    }

    private Report mapReport(){
        try {
            int typeOfReport = Integer.parseInt(request.getParameter("TypeOfReport"));
            String data = request.getParameter("date");
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            Timestamp timestamp = getTimestamp(date1);
            double incomeAmount = Double.parseDouble(request.getParameter("incomeAmount"));
            double taxAmount = Double.parseDouble(request.getParameter("taxAmount"));
            double fine = Double.parseDouble(request.getParameter("fine"));
            double penny = Double.parseDouble(request.getParameter("penny"));
            String userComment = request.getParameter("userComment");
            int idReport = Integer.parseInt(request.getParameter("idReport"));
            Report report = new Report();
            report.setIdReport(idReport);
            report.setIdType(typeOfReport);
         //   report.setStatus(Status.SUBMITTED);
            report.setDate(timestamp);
            report.setIncomeSum(incomeAmount);
            report.setTaxSum(taxAmount);
            report.setFine(fine);
            report.setPenny(penny);
            report.setCommentUser(userComment);
            return report;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    private  Timestamp getTimestamp(Date date) { return date == null ? null : new java.sql.Timestamp(date.getTime()); }
}
