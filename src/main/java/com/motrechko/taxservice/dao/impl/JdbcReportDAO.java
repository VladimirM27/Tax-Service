package com.motrechko.taxservice.dao.impl;

import com.motrechko.taxservice.dao.ConnectionFactory;
import com.motrechko.taxservice.dao.ReportDAO;
import com.motrechko.taxservice.model.Report;
import com.motrechko.taxservice.model.ReportView;
import com.motrechko.taxservice.enums.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class JdbcReportDAO implements ReportDAO {
    private static final Logger logger = LogManager.getLogger(JdbcReportDAO.class);


    @Override
    public boolean create(Report report) throws MySQLException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            addReport(connection,report);
            connection.commit();
            return true;
        } catch (SQLException | MySQLException e){
            ConnectionFactory.rollback(connection);
            logger.warn("Cannot create new report", e);
            throw new MySQLException("Cannot create new report", e);
        }
        finally {
            ConnectionFactory.close(connection);
        }
    }

    @Override
    public void update(Report report) throws MySQLException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            updateReportInfo(connection,report);
            connection.commit();
        } catch (SQLException e) {
            ConnectionFactory.rollback(connection);
            throw new MySQLException("cannot update report info",e);
        } finally {
            ConnectionFactory.close(connection);
        }
    }

    @Override
    public void updateUserReport(Report report) throws MySQLException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            updateReportInfoUser(connection,report);
            connection.commit();
        } catch (SQLException e) {
            ConnectionFactory.rollback(connection);
            throw new MySQLException("cannot update report info", e);
        } finally {
            ConnectionFactory.close(connection);
        }
    }


    @Override
    public void delete() throws MySQLException {

    }

    @Override
    public Report getReportById(int reportID) throws MySQLException {
        try (Connection connection = ConnectionFactory.getConnection(true);
             PreparedStatement statement = connection.prepareStatement(MySQLQuery.SELECT_REPORT_BY_ID)){
            statement.setInt(1,reportID);
            ResultSet set = statement.executeQuery();
            set.next();
            return mapReport(set);
        } catch (SQLException e){
            throw new MySQLException("Cannot get report", e);
        }
    }

    @Override
    public List<ReportView> getUserReports(int userId) throws MySQLException {
        try(Connection connection = ConnectionFactory.getConnection(true);
            PreparedStatement st = connection.prepareStatement(MySQLQuery.SELECT_REPORTS_BY_USER);){
            st.setInt(1,userId);
            ResultSet rs = st.executeQuery();
            List<ReportView> reportViews = new ArrayList<>();
            while (rs.next()){
                ReportView reportView = mapReportView(rs);
                reportViews.add(reportView);
            }
            return reportViews;
        }catch (SQLException e){
            throw new MySQLException("cannot select user reports" , e);
        }
    }





    private void addReport(Connection connection, Report report) throws MySQLException {
        try (PreparedStatement statement = connection.prepareStatement(MySQLQuery.INSERT_INTO_REPORTS, Statement.RETURN_GENERATED_KEYS)){
            int c = executeReportPreparedStatement(report, statement, false);;
            if(c> 0){
                try(ResultSet set = statement.getGeneratedKeys()){
                    if(set.next()){
                        report.setIdReport(set.getInt(1));
                    }
                }
            }
        } catch (SQLException e){
            throw new MySQLException("Cannot insert new report" , e);
        }
    }



    private ReportView mapReportView(ResultSet rs) {
        try {
            ReportView reportView = new ReportView();
            reportView.setIdReport(rs.getInt("idReport"));
            reportView.setInspectorName(rs.getString("inspector name"));
            reportView.setInspectorLastname(rs.getString("inspector lastname"));
            reportView.setType(rs.getString("type"));
            reportView.setStatus(rs.getString("status"));
            reportView.setDate(rs.getDate("date"));
            return reportView;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private void updateReportInfoUser(Connection connection, Report report)throws MySQLException {
        try (PreparedStatement statement = connection.prepareStatement(MySQLQuery.UPDATE_REPORT_USER)){
            executeReportPreparedStatement(report, statement, true);
        } catch (SQLException e) {
            throw new MySQLException("cannot execute update statement",e);
        }
    }

    private int executeReportPreparedStatement(Report report, PreparedStatement statement, boolean isUpdate) throws SQLException {
        int i = 0;
        statement.setInt(++i,report.getIdUser());
        statement.setInt(++i,report.getIdType());
        statement.setString(++i, String.valueOf(report.getStatus()));
        statement.setDate(++i,  new Date(report.getDate().getTime()));
        statement.setDouble(++i,report.getIncomeSum());
        statement.setDouble(++i,report.getTaxSum());
        statement.setDouble(++i,report.getFine());
        statement.setDouble(++i,report.getPenny());
        statement.setString(++i,report.getCommentUser());
        if(isUpdate)
            statement.setInt(++i,report.getIdReport());
        return statement.executeUpdate();
    }

    private void updateReportInfo(Connection connection, Report report) throws MySQLException {
        try (PreparedStatement statement = connection.prepareStatement(MySQLQuery.UPDATE_REPORT_INSPECTOR)){
            int i = 0;
            statement.setString(++i, String.valueOf(report.getStatus()));
            statement.setString(++i, report.getCommentInspector());
            statement.setInt(++i, report.getIdInspector());
            statement.setInt(++i, report.getIdReport());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new MySQLException("cannot execute update statement",e);
        }
    }




    private Report mapReport(ResultSet set) {
        try {
            Report report = new Report();
            report.setIdReport(set.getInt("idReport"));
            report.setIdUser(set.getInt("idUser"));
            report.setIdInspector(set.getInt("idInspector"));
            report.setIdType(set.getInt("idType"));
            report.setStatus(Status.valueOf(set.getString("status")));
            report.setDate(set.getDate("date"));
            report.setIncomeSum(set.getDouble("profitSum"));
            report.setTaxSum(set.getDouble("taxSum"));
            report.setFine(set.getDouble("fine"));
            report.setPenny(set.getDouble("penny"));
            report.setCommentUser(set.getString("commentUser"));
            report.setCommentInspector(set.getString("commentInspector"));
            return report;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

