package com.motrechko.taxservice.dao.mysql;

import com.motrechko.taxservice.dao.ConnectionPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLConnectionPool implements ConnectionPool {
    private static MySQLConnectionPool instance = null;
    public static DataSource connectionPool = null;


    private MySQLConnectionPool(){
        connectionPool = getPooledConnection();
    }

    public static MySQLConnectionPool getInstance(){
        if(instance == null) instance = new MySQLConnectionPool();
        return instance;
    }

    private static DataSource getPooledConnection() {
        Context ctx;
        DataSource ds = null;
        try{
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/taxdb");
        } catch (NamingException e){
            e.printStackTrace();
        }
        return ds;
    }

    @Override
    public Connection getConnection(boolean autocommit) {
        try {
            if(connectionPool == null) getPooledConnection();
            Connection con = connectionPool.getConnection();
            con.setAutoCommit(autocommit);
            return con;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
