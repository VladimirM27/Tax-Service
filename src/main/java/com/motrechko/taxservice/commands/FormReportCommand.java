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

public class FormReportCommand extends FrontCommand{
    @Override
    public CommandResponse process() throws ServletException, IOException {
        try {
            Report report = mapReport();
            ReportDAO reportDAO = DAOFactory.getInstance().getReportDAO();
            reportDAO.create(report);
            return new CommandResponse(Target.COMMAND,"Reports");
        } catch (MySQLException e) {
            return new CommandResponse(Target.JSP,FrontConstant.ERROR);
        }
    }

    private Report mapReport(){
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int typeOfReport = Integer.parseInt(request.getParameter("TypeOfReport"));
            java.lang.String data = request.getParameter("date");
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            Timestamp timestamp = getTimestamp(date1);
            double incomeAmount = Double.parseDouble(request.getParameter("incomeAmount"));
            double taxAmount = Double.parseDouble(request.getParameter("taxAmount"));
            double fine = Double.parseDouble(request.getParameter("fine"));
            double penny = Double.parseDouble(request.getParameter("penny"));
            java.lang.String userComment = request.getParameter("userComment");
            Report report = new Report();
            report.setIdUser(userId);
            //report.setReportType(typeOfReport);
         //   report.setStatus(Status.SUBMITTED);
          //  report.setCreated(timestamp);
            report.setTotalIncome(incomeAmount);
            report.setTotalDeductions(taxAmount);
            report.setTaxableIncome(fine);
            report.setTotalTaxOwned(penny);
            report.setCommentUser(userComment);
            return report;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    private  Timestamp getTimestamp(Date date) { return date == null ? null : new java.sql.Timestamp(date.getTime()); }
}
