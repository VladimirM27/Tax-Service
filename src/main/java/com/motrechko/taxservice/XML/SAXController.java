package com.motrechko.taxservice.XML;

import com.motrechko.taxservice.model.Report;
import jakarta.servlet.ServletContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


/**
 * A SAX Controller for parsing and validating XML reports against an XSD schema.
 */

public class SAXController extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger(SAXController.class);
    private static final String XSD_PATH = "/WEB-INF/report.xsd";
    private final String xmlFilePath;

    /**
     * Creates a new SAXController instance with the specified XML file path.
     *
     * @param xmlFilePath the path of the XML file to parse and validate.
     */
    public SAXController(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    /**
     * Parses the XML report file and returns a Report object.
     *
     * @return the parsed Report object.
     */
    public Report parse()  {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SaxParseHandler handler = new SaxParseHandler();
        SAXParser parser = null;
        InputSource inputSource = new InputSource(xmlFilePath);
        inputSource.setEncoding(StandardCharsets.UTF_8.displayName());
        try {
            parser = factory.newSAXParser();
            parser.parse(inputSource,handler);
        } catch (SAXException | IOException| ParserConfigurationException e ){
            logger.warn("Exception during parsing",e);
        }

        return handler.getResult();
    }



    /**
     * Validates the XML file against the XSD schema.
     *
     * @param servletContext the servlet context.
     * @return true if the XML file is valid against the XSD schema, false otherwise.
     */
    public  boolean validateXMLSchema(ServletContext servletContext) {
        try (InputStream xsdStream = servletContext.getResourceAsStream(XSD_PATH)) {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsdStream));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFilePath)));
            return true;
        } catch (IOException | SAXException e) {
            logger.warn("Error validating XML file against XSD schema", e);
            return false;
        }
    }
}


