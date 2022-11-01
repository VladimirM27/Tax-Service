package com.epam.motrechko.db.dao;

import com.epam.motrechko.db.entity.AdminReportView;
import com.epam.motrechko.db.entity.UnverifiedReportsView;
import com.epam.motrechko.db.entity.User;
import com.epam.motrechko.db.mysql.MySQLException;

import java.util.List;

public interface AdminDAO {
    List<AdminReportView> getAllUnverifiedReports(int inspectorId) throws MySQLException;
    UnverifiedReportsView getUnverifiedReports(int idUser) throws MySQLException;
}
