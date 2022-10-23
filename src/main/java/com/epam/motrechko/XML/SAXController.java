package com.epam.motrechko.XML;

import com.epam.motrechko.db.entity.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;




public class SAXController extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger(SAXController.class);
    private String xmlFileName;

    public SAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    private Report parse() throws XMLException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SaxParseHandler handler = new SaxParseHandler();
        SAXParser parser = null;
        InputSource inputSource = new InputSource(xmlFileName);
        inputSource.setEncoding(StandardCharsets.UTF_8.displayName());
        try {
            parser = factory.newSAXParser();
        } catch (Exception e ){
            logger.warn("Open sax parser error ",e);
            throw new XMLException("Open sax parser error",e);
        }


        try {
            parser.parse(inputSource,handler);
        } catch (SAXException | IOException e){
            logger.warn("Exception during parsing",e);
            throw new XMLException("Exception during parsing",e);
        }
        return handler.getResult();
    }

}
