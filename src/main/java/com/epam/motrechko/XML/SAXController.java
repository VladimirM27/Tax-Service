package com.epam.motrechko.XML;

import com.epam.motrechko.db.entity.Report;
import jakarta.servlet.ServletContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;




public class SAXController extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger(SAXController.class);
    private String xmlFileName;

    public SAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Report parse()  {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SaxParseHandler handler = new SaxParseHandler();
        SAXParser parser = null;
        InputSource inputSource = new InputSource(xmlFileName);
        inputSource.setEncoding(StandardCharsets.UTF_8.displayName());
        try {
            parser = factory.newSAXParser();
        } catch (Exception e ){
            logger.warn("Open sax parser error ",e);

        }

        try {
            parser.parse(inputSource,handler);
        } catch (SAXException | IOException e){
            logger.warn("Exception during parsing",e);
        }
        return handler.getResult();
    }

    public static boolean validateXMLSchema(ServletContext servletContext, String xmlPath){
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Schema schema = factory.newSchema(new StreamSource(servletContext.getResourceAsStream("/WEB-INF/report.xsd")));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            logger.warn("Cannot open XSD schema OR validate error",e);
            return false;
        }
        return true;
    }

}
