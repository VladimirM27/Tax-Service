package com.epam.motrechko.db.dao;

import com.epam.motrechko.db.entity.Report;
import com.epam.motrechko.db.entity.ReportView;
import com.epam.motrechko.db.mysql.MySQLException;

import java.util.List;


public interface ReportDAO {
    boolean create(Report report)  throws MySQLException;
    boolean update(Report report)  throws MySQLException;
    void delete()  throws MySQLException;
    Report getReportById(int reportID)throws MySQLException;
    List<ReportView> getUserReports(int userId) throws MySQLException;

}
