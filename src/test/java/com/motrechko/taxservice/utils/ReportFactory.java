package com.motrechko.taxservice.utils;

import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.ReportTypeDAO;
import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.model.ReportType;
import com.motrechko.taxservice.model.Status;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Random;


public class ReportFactory {
    private static final int MAX_ID = 1000;
    private static final int MAX_SUM = 100000;
    private static final int MAX_FINE = 10000;
    private static final int MAX_PENNY = 100;
    private static final String[] COMMENTS = {
            "Lorem ipsum dolor sit amet", "consectetur adipiscing elit",
            "sed do eiusmod tempor incididunt", "ut labore et dolore magna aliqua"
    };
    private static final Random random = new Random();

    public static Report createRandomReport() throws ParseException, MySQLException {
        Report report = new Report();
        report.setIdReport(random.nextInt(MAX_ID));
        report.setReportType(getRandomReportType());
        report.setStatus(Status.values()[random.nextInt(Status.values().length)]);
        report.setCreated(LocalDate.now());
        report.setTotalIncome(random.nextDouble() * MAX_SUM);
        report.setTotalDeductions(random.nextDouble() * MAX_SUM);
        report.setTaxableIncome(random.nextDouble() * MAX_FINE);
        report.setTotalTaxOwned(random.nextDouble() * MAX_PENNY);
        report.setCommentUser(getRandomComment());
        report.setCommentInspector(getRandomComment());
        return report;
    }

    private static String getRandomComment() {
        return COMMENTS[random.nextInt(COMMENTS.length)];
    }
    private static ReportType getRandomReportType() throws MySQLException {
        ReportTypeDAO reportTypeDAO= DAOFactory.getInstance().getReportTypeDAO();
        return reportTypeDAO.getReportTypeById(random.nextInt(1,10));
    }
}

