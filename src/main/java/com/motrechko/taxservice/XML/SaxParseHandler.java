package com.motrechko.taxservice.XML;

import com.motrechko.taxservice.exception.ReportException;
import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.model.Status;
import com.motrechko.taxservice.service.ReportTypeService;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;

/**
 * This class represents a SAX parser handler for parsing a report XML file.
 */
public class SaxParseHandler extends DefaultHandler {

    private static final String ID_USER = "idUser";
    private static final String ID_TYPE = "reportType";
    private static final String CREATED = "created";
    private static final String TOTAL_INCOME = "totalIncome";
    private static final String TOTAL_DEDUCTIONS = "totalDeductions";
    private static final String TAXABLE_INCOME = "taxableIncome";
    private static final String TOTAL_TAX_OWNED = "totalTaxOwned";
    private static final String TOTAL_PAID = "totalPaid";
    private static final String USER_COMMENT = "commentUser";

    private ReportTypeService reportTypeService;
    private final StringBuilder currentValue = new StringBuilder();
    private Report report;

    /**
     * Returns the report object parsed by this handler.
     *
     * @return the parsed report object
     */
    public Report getResult() {
        return report;
    }

    @Override
    public void startDocument() {
        report = new Report();
        reportTypeService = new ReportTypeService();
    }

    @Override
    public void endDocument() {
        report.setStatus(Status.SUBMITTED);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentValue.setLength(0);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            switch (qName) {
                case ID_USER -> report.setIdUser(Integer.parseInt(currentValue.toString()));
                case ID_TYPE -> report.setReportType(reportTypeService.getReportTypeById(Integer.parseInt(currentValue.toString())));
                case CREATED -> report.setCreated(LocalDate.parse(currentValue.toString()));
                case TOTAL_INCOME -> report.setTotalIncome(Double.parseDouble(currentValue.toString()));
                case TOTAL_DEDUCTIONS -> report.setTotalDeductions(Double.parseDouble(currentValue.toString()));
                case TAXABLE_INCOME -> report.setTaxableIncome(Double.parseDouble(currentValue.toString()));
                case TOTAL_PAID -> report.setTotalPaid(Double.parseDouble(currentValue.toString()));
                case USER_COMMENT -> report.setCommentUser(currentValue.toString());
                case TOTAL_TAX_OWNED -> report.setTotalTaxOwned(Double.parseDouble(currentValue.toString()));
            }
        } catch (NumberFormatException | ReportException e) {
            throw new SAXException("Failed to parse report", e);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        currentValue.append(ch, start, length);
    }

}
