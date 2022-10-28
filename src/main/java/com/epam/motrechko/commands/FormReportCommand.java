package com.epam.motrechko.commands;

import com.epam.motrechko.FrontConstant;
import com.epam.motrechko.db.dao.DAOFactory;
import com.epam.motrechko.db.dao.ReportDAO;
import com.epam.motrechko.db.entity.Report;
import com.epam.motrechko.db.mysql.MySQLException;
import com.epam.motrechko.enums.Status;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormReportCommand extends FrontCommand{
    @Override
    public String process() throws ServletException, IOException {
        try {
            Report report = mapReport();
            ReportDAO reportDAO = DAOFactory.getInstance().getReportDAO();
            reportDAO.create(report);
            return FrontConstant.REPORTS_USER;
        } catch (MySQLException e) {
            return FrontConstant.ERROR;
        }
    }

    private Report mapReport(){
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int TypeOfReport = Integer.parseInt(request.getParameter("TypeOfReport"));
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
            report.setIdType(TypeOfReport);
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
