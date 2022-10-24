package com.epam.motrechko.JSON;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import jakarta.servlet.ServletContext;

import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class JSONController {


    public static boolean validateJSONSchema(ServletContext servletContext, String xmlPath) throws IOException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        ObjectMapper mapper = new ObjectMapper();
        JsonSchema jsonSchema = factory.getSchema(
                new StreamSource(servletContext.getResourceAsStream("/WEB-INF/schema.json")).getInputStream());
        JsonNode jsonNode = mapper.readTree( new FileInputStream(new File(xmlPath)));
        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
       return errors.isEmpty();
    }
}
