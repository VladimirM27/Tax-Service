package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.model.AdminReportView;
import com.motrechko.taxservice.model.UnverifiedReportsView;
import com.motrechko.taxservice.dao.mysql.MySQLException;

import java.util.List;

public interface AdminDAO {
    List<AdminReportView> getAllUnverifiedReports(int inspectorId) throws MySQLException;
    UnverifiedReportsView getUnverifiedReports(int idUser) throws MySQLException;
}
