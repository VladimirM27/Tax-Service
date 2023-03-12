package com.motrechko.taxservice.utils;

import com.motrechko.taxservice.dao.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public final  class StatementUtils {
    private StatementUtils() {throw new AssertionError("This class should not be instantiated.");}
    private static final Logger logger = LogManager.getLogger(StatementUtils.class);

    public static void close (PreparedStatement statement){
        try {
            statement.close();
        } catch (SQLException e) {
            logger.error("Error occurred while closing statement", e);
        }
    }
}
