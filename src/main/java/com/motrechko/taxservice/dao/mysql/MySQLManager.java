package com.motrechko.taxservice.dao.mysql;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLManager {
    public static void close(Connection con)  {
        if(con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void rollback(Connection connection) {
        if(connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
