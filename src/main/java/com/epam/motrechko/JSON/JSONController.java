package com.epam.motrechko.JSON;

import com.epam.motrechko.db.entity.Report;
import com.epam.motrechko.enums.Status;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import jakarta.servlet.ServletContext;

import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

public class JSONController {


    public static boolean validateJSONSchema(ServletContext servletContext, java.lang.String xmlPath) throws IOException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        ObjectMapper mapper = new ObjectMapper();
        JsonSchema jsonSchema = factory.getSchema(
                new StreamSource(servletContext.getResourceAsStream("/WEB-INF/schema.json")).getInputStream());
        JsonNode jsonNode = mapper.readTree( new FileInputStream(new File(xmlPath)));
        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
       return errors.isEmpty();
    }


    public static Report parseJSON(java.lang.String uploadPath) throws FileNotFoundException {

        Gson gson = new Gson();
        Reader reader = new FileReader(uploadPath);
        Report report = gson.fromJson(reader,Report.class);
        Date oldDate = report.getDate();
        Timestamp timestamp2 = getTimestamp(oldDate);
        report.setDate(timestamp2);
        report.setStatus(Status.SUBMITTED);
        return report;
    }

    private static Timestamp getTimestamp(Date date) { return date == null ? null : new java.sql.Timestamp(date.getTime()); }
}
