package com.motrechko.taxservice.utils;

import com.motrechko.taxservice.enums.Status;
import com.motrechko.taxservice.model.Report;

import java.text.ParseException;
import java.util.Date;
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

    public static Report createRandomReport() throws ParseException {
        Report report = new Report();
        report.setIdReport(random.nextInt(MAX_ID));
        report.setIdType(random.nextInt(6));
        report.setStatus(Status.values()[random.nextInt(Status.values().length)]);
        report.setDate(new Date());
        report.setIncomeSum(random.nextDouble() * MAX_SUM);
        report.setTaxSum(random.nextDouble() * MAX_SUM);
        report.setFine(random.nextDouble() * MAX_FINE);
        report.setPenny(random.nextDouble() * MAX_PENNY);
        report.setCommentUser(getRandomComment());
        report.setCommentInspector(getRandomComment());
        return report;
    }

    private static String getRandomComment() {
        return COMMENTS[random.nextInt(COMMENTS.length)];
    }
}

