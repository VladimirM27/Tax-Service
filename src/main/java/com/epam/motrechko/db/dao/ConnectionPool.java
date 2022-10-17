package com.epam.motrechko.db.dao;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection(boolean autocommit);
}
