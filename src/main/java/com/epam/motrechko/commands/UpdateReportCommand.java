package com.epam.motrechko.commands;

import com.epam.motrechko.FrontConstant;
import com.epam.motrechko.db.dao.DAOFactory;
import com.epam.motrechko.db.dao.ReportDAO;
import com.epam.motrechko.db.entity.Report;
import com.epam.motrechko.db.mysql.MySQLException;
import com.epam.motrechko.enums.Status;
import com.epam.motrechko.enums.Target;
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
            reportDAO.updateUser(report);
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
            report.setStatus(Status.SUBMITTED);
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
