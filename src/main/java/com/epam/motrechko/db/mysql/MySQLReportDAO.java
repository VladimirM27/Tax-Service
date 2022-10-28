package com.epam.motrechko.db.mysql;

import com.epam.motrechko.db.dao.ReportDAO;
import com.epam.motrechko.db.entity.Report;
import com.epam.motrechko.db.entity.ReportView;
import com.epam.motrechko.db.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLReportDAO implements ReportDAO {
    private static final Logger logger = LogManager.getLogger(MySQLReportDAO.class);
    public List<ReportView> getUserReports(int userId) throws MySQLException {
        try(Connection connection = MySQLConnectionPool.getInstance().getConnection(true);
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

    @Override
    public boolean create(Report report) throws MySQLException {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getInstance().getConnection(false);
            addReport(connection,report);
            connection.commit();
            return true;
        } catch (SQLException | MySQLException e){
            MySQLManager.rollback(connection);
            logger.warn("Cannot create new report"  , e);
            throw new MySQLException("Cannot create new report",e);
        }
        finally {
            MySQLManager.close(connection);
        }
    }



    private void addReport(Connection connection, Report report) throws MySQLException {
        try (PreparedStatement statement = connection.prepareStatement(MySQLQuery.INSERT_INTO_REPORTS, Statement.RETURN_GENERATED_KEYS)){
            int i = 0;
            statement.setInt(++i,report.getIdUser());
            statement.setInt(++i,report.getIdType());
            statement.setString(++i, String.valueOf(report.getStatus()));
            statement.setDate(++i,  new java.sql.Date(report.getDate().getTime()));
            statement.setDouble(++i,report.getIncomeSum());
            statement.setDouble(++i,report.getTaxSum());
            statement.setDouble(++i,report.getFine());
            statement.setDouble(++i,report.getPenny());
            statement.setString(++i,report.getCommentUser());
            int c = statement.executeUpdate();
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

    @Override
    public void update() throws MySQLException {

    }

    @Override
    public void delete() throws MySQLException {

    }
}

