package com.motrechko.taxservice.dao.impl;

import com.motrechko.taxservice.dao.ConnectionFactory;
import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.ReportDAO;
import com.motrechko.taxservice.dao.exception.MySQLException;
import com.motrechko.taxservice.dao.queries.ReportQueries;
import com.motrechko.taxservice.model.*;
import com.motrechko.taxservice.utils.StatementUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of ReportDAO that uses JDBC to interact with the database.
 */
public class JdbcReportDAO implements ReportDAO {
    private static final Logger logger = LogManager.getLogger(JdbcReportDAO.class);


    /**
     * Creates a new report in the database.
     *
     * @param report the report to create
     * @return true if the report was successfully created, false otherwise
     * @throws MySQLException if there was an error creating the report
     */
    @Override
    public boolean create(Report report) throws MySQLException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            addReport(connection,report);
            connection.commit();
            logger.info("New report with id {} created successfully", report.getIdReport());
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
    public List<AdminReportView> getAllUnverifiedReports(int inspectorId) throws MySQLException {
        return null;
    }

    @Override
    public UnverifiedReportsView getUnverifiedReports(int idUser) throws MySQLException {
        return null;
    }

    /**
     * Updates the report information for a user.
     *
     * @param report the report to update
     * @throws MySQLException if there is a problem updating the report
     */
    @Override
    public void update(Report report) throws MySQLException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            PreparedStatement statement = connection.prepareStatement(ReportQueries.UPDATE_REPORT);
            executeReportPreparedStatement(report, statement, true);
            logger.info("Report updated with id: {}" , report.getIdReport());
            connection.commit();
        } catch (SQLException e) {
            ConnectionFactory.rollback(connection);
            logger.warn("failed to update report with ID: {}" , report.getIdReport(), e);
            throw new MySQLException("cannot update report info", e);
        } finally {
            ConnectionFactory.close(connection);
        }
    }


    /**
     * Deletes a report from the database.
     *
     * @param id the ID of the user to delete
     * @throws MySQLException if a database access error occurs
     */
    @Override
    public void delete(int id) throws MySQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection(false);
            statement = connection.prepareStatement(ReportQueries.DELETE_REPORT);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            logger.info("Deleted {} row(s) from the reports table", rowsDeleted);
            connection.commit();
        } catch (SQLException e) {
            ConnectionFactory.rollback(connection);
            logger.warn("failed to delete a report with ID: {}" , id, e);
            throw new MySQLException("Error deleting report from the database", e);
        } finally {
            StatementUtils.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Retrieves a report with the specified ID from the database.
     *
     * @param reportID the ID of the report to retrieve
     * @return the report with the specified ID
     * @throws MySQLException if there was an error retrieving the report from the database
     */
    @Override
    public Report getReportById(int reportID) throws MySQLException {
        try (Connection connection = ConnectionFactory.getConnection(true);
             PreparedStatement statement = connection.prepareStatement(ReportQueries.SELECT_REPORT_BY_ID)){
            statement.setInt(1,reportID);
            ResultSet set = statement.executeQuery();
            set.next();
            return mapReport(set);
        } catch (SQLException e){
            logger.error("Error getting report with ID: {}", reportID, e);
            throw new MySQLException("Cannot get report", e);
        }
    }

    @Override
    public List<Report> getReportsByInspector(int idInspector) throws MySQLException {
        return null;
    }

    /**
     * Retrieves a list of report views for the specified user from the database.
     *
     * @param userId the ID of the user whose reports to retrieve
     * @return a list of report views for the specified user
     * @throws MySQLException if there was an error retrieving the reports from the database
     */
    @Override
    public List<ReportView> getReportViewByUserId(int userId) throws MySQLException {
        try(Connection connection = ConnectionFactory.getConnection(true);
            PreparedStatement st = connection.prepareStatement(ReportQueries.SELECT_REPORTVIEW_BY_USER)){
            st.setInt(1,userId);
            ResultSet rs = st.executeQuery();
            List<ReportView> reportViews = new ArrayList<>();
            while (rs.next()){
                ReportView reportView = mapReportView(rs);
                reportViews.add(reportView);
            }
            return reportViews;
        }catch (SQLException e){
            logger.error("Cannot select user reports from user with id : {}",userId, e);
            throw new MySQLException("cannot select user reports" , e);
        }
    }




    /**
     * Adds a report to the database.
     *
     * @param connection the connection to use to interact with the database
     * @param report     the report to add
     * @throws MySQLException if an error occurs while interacting with the database
     */
    private void addReport(Connection connection, Report report) throws MySQLException {
        try (PreparedStatement statement = connection.prepareStatement(ReportQueries.INSERT_REPORT, Statement.RETURN_GENERATED_KEYS)){
            int c = executeReportPreparedStatement(report, statement, false);
            if(c> 0){
                try(ResultSet set = statement.getGeneratedKeys()){
                    if(set.next()){
                        report.setIdReport(set.getInt(1));
                    }
                }
            }
        } catch (SQLException e){
            logger.warn("Failed to insert new report to user with id : {} ", report.getIdUser(), e);
            throw new MySQLException("Cannot insert new report" , e);
        }
    }



    /**
     Maps a ResultSet to a ReportView object.
     @param rs the ResultSet containing the data to be mapped
     @return the ReportView object containing the mapped data
     */
    private ReportView mapReportView(ResultSet rs) {
        try {
            ReportView reportView = new ReportView();
            reportView.setIdReport(rs.getInt("idReport"));
            reportView.setInspectorName(rs.getString("inspectorName"));
            reportView.setInspectorLastname(rs.getString("inspectorLastName"));
            reportView.setType(DAOFactory.getInstance().getReportTypeDAO().getReportTypeByName("type"));
            reportView.setStatus(Status.valueOf(rs.getString("status")));
            reportView.setDate(rs.getDate("date"));
            return reportView;
        } catch (SQLException | MySQLException e) {
            logger.warn("Cannot map ResultSet to ReportView");
            return null;
        }
    }
    /**
     Maps and executes a Report object to a PreparedStatement object for updating or adding the report in the database.
     @param report The report object containing the updated information.
     @param statement The PreparedStatement object to be mapped to the Report object.
     @param isUpdate Is the statement an update
     @return The number of rows updated in the database.
     @throws SQLException If an error occurs while executing the SQL query.
     */
    private int executeReportPreparedStatement(Report report, PreparedStatement statement, boolean isUpdate) throws SQLException {
        int i = 0;
        statement.setInt(++i,report.getIdUser());
        statement.setInt(++i, report.getIdInspector());
        statement.setInt(++i,report.getReportType().getReportTypeId());
        statement.setString(++i, String.valueOf(report.getStatus()));
        statement.setDate(++i, Date.valueOf(report.getUtilDate()));
        statement.setDouble(++i,report.getTotalIncome());
        statement.setDouble(++i,report.getTotalDeductions());
        statement.setDouble(++i,report.getTaxableIncome());
        statement.setDouble(++i,report.getTotalTaxOwned());
        statement.setDouble(++i,report.getTotalPaid());
        statement.setString(++i,report.getCommentUser());
        statement.setString(++i,report.getCommentInspector());
        if(isUpdate)
            statement.setInt(++i,report.getIdReport());
        return statement.executeUpdate();
    }

    /**
     *  Mapped ResultSet object to Report class object
     * @param set the ResultSet containing the data to be mapped
     * @return the Report object containing the mapped data
     */
    private Report mapReport(ResultSet set) throws MySQLException {
        try {
            Report report = new Report();
            report.setIdReport(set.getInt("idReport"));
            report.setIdUser(set.getInt("idUser"));
            report.setIdInspector(set.getInt("idInspector"));
            int idType = set.getInt("idType");
            ReportType reportType = DAOFactory.getInstance().getReportTypeDAO().getReportTypeById(idType);
            report.setReportType(reportType);
            report.setStatus(Status.valueOf(set.getString("status")));
            report.setUtilDate(LocalDate.parse(set.getString("created")));
            report.setTotalIncome(set.getDouble("total_income"));
            report.setTotalDeductions(set.getDouble("total_deductions"));
            report.setTaxableIncome(set.getDouble("taxable_income"));
            report.setTotalTaxOwned(set.getDouble("total_tax_owned"));
            report.setTotalPaid(set.getDouble("total_paid"));
            report.setCommentUser(set.getString("commentUser"));
            report.setCommentInspector(set.getString("commentInspector"));
            return report;
        } catch (SQLException e) {
            logger.warn("Cannot map report from ResultSet to Report object");
            throw new MySQLException("Error during report mapping");
        }
    }
}

