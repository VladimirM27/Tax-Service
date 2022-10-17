package com.epam.motrechko.db;

import java.sql.Connection;

public class ManagerDB {
    private static final String CONNECTION_URL_PROPERTY = "connection.url";
    private static final String CONNECTION__PROPERTIES_NAME = "app.properties";
    private String url;
    private Connection connection;

}
