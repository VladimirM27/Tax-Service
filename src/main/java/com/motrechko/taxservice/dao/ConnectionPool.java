package com.motrechko.taxservice.dao;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection(boolean autocommit);
}
