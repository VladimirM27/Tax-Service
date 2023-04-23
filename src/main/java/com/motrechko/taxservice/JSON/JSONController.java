package com.motrechko.taxservice.JSON;

import com.google.gson.GsonBuilder;
import com.motrechko.taxservice.model.Report;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.motrechko.taxservice.model.Status;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import jakarta.servlet.ServletContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;


public class JSONController {

    private static final Logger logger = LogManager.getLogger(JSONController.class);
    private JSONController() {
        throw new AssertionError("Utility class");
    }

    /**
     * Validates a JSON file against a JSON schema.
     *
     * @param servletContext the servlet context
     * @param path the path to the JSON file
     * @return true if the JSON is valid, false otherwise
     */
    public static boolean validateJSONSchema(ServletContext servletContext, String path){
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        try (InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/schema.json")) {
            JsonSchema jsonSchema = factory.getSchema(inputStream);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(Files.newInputStream(Paths.get(path)));
            return jsonSchema.validate(jsonNode).isEmpty();
        } catch (IOException e){
            logger.error("Error validating JSON file: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Parses a JSON file into a Report object.
     *
     * @param uploadPath the path to the JSON file
     * @return the parsed Report object
     * @throws FileNotFoundException if the file does not exist
     */
    public static Report parseJSON(String uploadPath) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        try (Reader reader = Files.newBufferedReader(Paths.get(uploadPath))) {
            Report report = gson.fromJson(reader, Report.class);
            report.setStatus(Status.SUBMITTED);
            return report;
        } catch (IOException e) {
            logger.error("Error parsing JSON file: {}", e.getMessage());
            throw new FileNotFoundException(e.getMessage());
        }
    }
}
