package com.epam.motrechko.XML;

import com.epam.motrechko.db.entity.Report;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParseHandler extends DefaultHandler {

    private static final String REPORT = "report";
    private static final String
    private static final String
    private static final String
    
    
    
    private StringBuilder currentValue = new StringBuilder();
    private Report report;

    public Report getResult(){return  report;}

    @Override
    public void startDocument() throws SAXException {
        report = new Report();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        currentValue.setLength(0);

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue.append(ch,start,length);
    }
}
