package com.motrechko.taxservice.XML;

import com.motrechko.taxservice.model.Report;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaxParseHandler extends DefaultHandler {


    private static final java.lang.String ID_USER = "idUser";
    private static final java.lang.String ID_TYPE = "idType";

    private static final java.lang.String DATE = "date";
    private static final java.lang.String INCOME_SUM = "incomeSum";
    private static final java.lang.String TAX_SUM = "taxSum";
    private static final java.lang.String FINE = "fine";
    private static final java.lang.String PENNY = "penny";
    private static final java.lang.String USER_COMMENT = "commentUser";


    
    private StringBuilder currentValue = new StringBuilder();
    private Report report;

    public Report getResult(){return  report;}

    @Override
    public void startDocument() throws SAXException {
        report = new Report();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
      //  report.setStatus(Status.SUBMITTED);
    }

    @Override
    public void startElement(java.lang.String uri, java.lang.String localName, java.lang.String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        currentValue.setLength(0);

    }

    @Override
    public void endElement(java.lang.String uri, java.lang.String localName, java.lang.String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch (qName){
            case ID_USER:
                report.setIdUser(Integer.parseInt(currentValue.toString()));
                break;
            case ID_TYPE:
                //report.setReportType(Integer.parseInt(currentValue.toString()));
                break;
            case DATE:
                setDate();
                break;
            case INCOME_SUM:
                report.setTotalIncome(Double.parseDouble(currentValue.toString()));
                break;
            case TAX_SUM:
                report.setTotalDeductions(Double.parseDouble(currentValue.toString()));
                break;
            case FINE:
                report.setTaxableIncome(Double.parseDouble(currentValue.toString()));
                break;
            case PENNY:
                report.setTotalTaxOwned(Double.parseDouble(currentValue.toString()));
                break;
            case USER_COMMENT:
                report.setCommentUser(currentValue.toString());
                break;

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue.append(ch,start,length);
    }

    private void setDate() throws SAXException {
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(currentValue.toString());
            Timestamp timestamp = getTimestamp(date1);
            //report.setCreated(timestamp);
        } catch (ParseException e) {
            throw new SAXException("cannot set date", e);
        }
    }
    private static Timestamp getTimestamp(Date date) { return date == null ? null : new java.sql.Timestamp(date.getTime()); }
}
