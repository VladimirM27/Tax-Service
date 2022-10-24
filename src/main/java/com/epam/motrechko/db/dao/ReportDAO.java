package com.epam.motrechko.db.dao;

import com.epam.motrechko.db.entity.Report;
import com.epam.motrechko.db.mysql.MySQLException;



public interface ReportDAO {
    boolean create(Report report)  throws MySQLException;
    void update()  throws MySQLException;
    void delete()  throws MySQLException;
}
