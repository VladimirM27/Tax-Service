package com.motrechko.taxservice.dao.impl;

import com.motrechko.taxservice.dao.AdminDAO;
import com.motrechko.taxservice.dao.ConnectionFactory;
import com.motrechko.taxservice.model.AdminReportView;
import com.motrechko.taxservice.model.UnverifiedReportsView;
import com.motrechko.taxservice.enums.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * An implementation of the AdminDAO interface that uses JDBC to interact with a database.
 */
public class JdbcAdminDAO implements AdminDAO {
    private static final Logger logger = LogManager.getLogger(JdbcAdminDAO.class);

    /**
    * Retrieves all unverified reports from the database for a particular inspector.
    *
    * @param inspectorId the id of the inspector whose unverified reports will be retrieved
    * @return a list of unverified reports for a particular inspector
    * @throws MySQLException if there is an error in the SQL query execution
    */
    @Override
    public List<AdminReportView> getAllUnverifiedReports(int inspectorId) throws MySQLException {
        try (Connection connection = ConnectionFactory.getConnection(true);
             PreparedStatement st = connection.prepareStatement(MySQLQuery.SELECT_ALL_UNVERIFIED_REPORTS)) {
            st.setInt(1, inspectorId);
            ResultSet rs = st.executeQuery();
            List<AdminReportView> reportViews = new ArrayList<>();
            while (rs.next()) {
                AdminReportView reportView = mapReportView(rs);
                reportViews.add(reportView);
            }
            return reportViews;
        } catch (SQLException | MySQLException e) {
            logger.error("Failed to retrieve all unverified reports for inspector with id: " + inspectorId, e);
            throw new MySQLException("Cannot retrieve unverified reports for inspector with id: " + inspectorId, e);
        }
    }



    /**
     * Retrieves the details of a specific unverified report for a user from the database.
     *
     * @param userId the id of the user whose unverified report will be retrieved
     * @return an object containing the details of the unverified report for a user
     * @throws MySQLException if there is an error in the SQL query execution
     */
    @Override
    public UnverifiedReportsView getUnverifiedReports(int userId) throws MySQLException {
        try (Connection connection = ConnectionFactory.getConnection(true);
             PreparedStatement preparedStatement = connection.prepareStatement(MySQLQuery.SELECT_USERS_UNVERIFIED_REPORTS)) {

            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return mapUnverifiedReportsView(rs);
        } catch (SQLException e) {
            logger.error("Failed to retrieve unverified report for user with id: " + userId, e);
            throw new MySQLException("Cannot retrieve unverified report for user with id: " + userId, e);
        }
    }


    /**
     * Maps a ResultSet object to an UnverifiedReportsView object.
     *
     * @param rs the ResultSet object that will be mapped to an UnverifiedReportsView object
     * @return an object containing the details of the unverified report for a user
     * @throws MySQLException if there is an error in the ResultSet mapping
     */
    private UnverifiedReportsView mapUnverifiedReportsView(ResultSet rs) throws MySQLException {
        try {
            UnverifiedReportsView unverifiedReportsView = new UnverifiedReportsView();
            unverifiedReportsView.setType(rs.getString("type"));
            unverifiedReportsView.setFirstName(rs.getString("firstName"));
            unverifiedReportsView.setLastName(rs.getString("lastName"));
            unverifiedReportsView.setEmail(rs.getString("email"));
            unverifiedReportsView.setTIN(rs.getLong("TIN"));
            unverifiedReportsView.setCity(rs.getString("city"));
            unverifiedReportsView.setStreet(rs.getString("street"));
            unverifiedReportsView.setNumberOfBuilding(rs.getString("NumberOfBuilding"));
            unverifiedReportsView.setDate(rs.getDate("date"));
            unverifiedReportsView.setProfitSum(rs.getDouble("profitSum"));
            unverifiedReportsView.setTaxSum(rs.getDouble("taxSum"));
            unverifiedReportsView.setFine(rs.getDouble("fine"));
            unverifiedReportsView.setPenny(rs.getDouble("penny"));
            unverifiedReportsView.setUserComment(rs.getString("commentUser"));
            return unverifiedReportsView;
        } catch (SQLException e) {
            logger.error("Failed map Unverified Reports View: " , e);
            throw new MySQLException("cannot map Unverified Reports View",e);
        }
    }

    /**
     Maps a ResultSet to an AdminReportView object.
     @param rs The ResultSet to be mapped.
     @return An AdminReportView object mapped from the ResultSet.
     @throws MySQLException If there is an error accessing the ResultSet.
     */
    private AdminReportView mapReportView(ResultSet rs) throws MySQLException {
        try {
            AdminReportView adminReportView = new AdminReportView();
            adminReportView.setIdReport(rs.getInt("idReport"));
            adminReportView.setType(rs.getString("type"));
            adminReportView.setStatus(Status.valueOf(rs.getString("status")));
            adminReportView.setDate(rs.getDate("date"));
            adminReportView.setFirstName(rs.getString("firstName"));
            adminReportView.setLastName(rs.getString("lastName"));
            logger.debug("Successfully mapped ResultSet to AdminReportView object");
            return adminReportView;
        } catch (SQLException e) {
            logger.error("SQLException occurred while mapping ResultSet to AdminReportView object", e);
            throw new MySQLException("cannot map AdminReportView",e);
        }
    }
}

