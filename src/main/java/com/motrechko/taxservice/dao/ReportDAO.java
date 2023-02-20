package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.model.ReportView;
import com.motrechko.taxservice.dao.mysql.MySQLException;

import java.util.List;


public interface ReportDAO {
    boolean create(Report report)  throws MySQLException;
    void update(Report report)  throws MySQLException;
    void updateUser(Report report)  throws MySQLException;
    void delete()  throws MySQLException;
    Report getReportById(int reportID)throws MySQLException;
    List<ReportView> getUserReports(int userId) throws MySQLException;

}
