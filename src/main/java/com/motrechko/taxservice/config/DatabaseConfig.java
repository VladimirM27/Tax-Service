package com.motrechko.taxservice.config;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class reads the database properties from the db.properties file.
 */
public class DatabaseConfig {

    private final Properties properties;

    /**
     * Constructor that loads the database properties file into the properties object.
     * If there is any exception during the file loading, a runtime exception will be thrown.
     */
    public DatabaseConfig() {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read database properties file", e);
        }
    }

    /**
     * Returns the value of the "db.url" property from the properties object.
     *
     * @return the URL of the database
     */
    public String getUrl() {
        return properties.getProperty("db.url");
    }

    /**
     * Returns the value of the "db.username" property from the properties object.
     *
     * @return the username to connect to the database
     */
    public String getUsername() {
        return properties.getProperty("db.username");
    }

    /**
     * Returns the value of the "db.password" property from the properties object.
     *
     * @return the password to connect to the database
     */
    public String getPassword() {
        return properties.getProperty("db.password");
    }

    /**
     * Returns the value of the "db.driverClassName" property from the properties object.
     *
     * @return the class name of the database driver
     */
    public String getDriver() {
        return properties.getProperty("db.driverClassName");
    }
}
