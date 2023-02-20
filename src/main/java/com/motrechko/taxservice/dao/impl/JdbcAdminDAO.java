package com.motrechko.taxservice.dao.impl;

import com.motrechko.taxservice.dao.AdminDAO;
import com.motrechko.taxservice.model.AdminReportView;
import com.motrechko.taxservice.model.UnverifiedReportsView;
import com.motrechko.taxservice.enums.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcAdminDAO implements AdminDAO {
    @Override
    public List<AdminReportView> getAllUnverifiedReports(int inspectorId) throws MySQLException {
        try(Connection connection = MySQLConnectionPool.getInstance().getConnection(true);
        PreparedStatement st = connection.prepareStatement(MySQLQuery.SELECT_ALL_UNVERIFIED_REPORTS);){
            st.setInt(1,inspectorId);
            ResultSet rs = st.executeQuery();
            List<AdminReportView> reportViews = new ArrayList<>();
            while (rs.next()){
                AdminReportView reportView = mapReportView(rs);
                reportViews.add(reportView);
            }
            return reportViews;
        }catch (SQLException | MySQLException e){
            throw new MySQLException("cannot select unverified reports" , e);
        }
    }



    @Override
    public UnverifiedReportsView getUnverifiedReports(int userId) throws MySQLException {
        try(Connection connection = MySQLConnectionPool.getInstance().getConnection(true);
            PreparedStatement preparedStatement = connection.prepareStatement(MySQLQuery.SELECT_USERS_UNVERIFIED_REPORTS);){

            preparedStatement.setInt(1,userId);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return mapUnverifiedReportsView(rs);
        }catch (SQLException e){
            throw new MySQLException("cannot select unverified report" , e);
        }
    }

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
            throw new MySQLException("cannot map Unverified Reports View",e);
        }
    }

    private AdminReportView mapReportView(ResultSet rs) throws MySQLException {
        try {
            AdminReportView adminReportView = new AdminReportView();
            adminReportView.setIdReport(rs.getInt("idReport"));

            adminReportView.setType(rs.getString("type"));
            adminReportView.setStatus(Status.valueOf(rs.getString("status")));
            adminReportView.setDate(rs.getDate("date"));
            adminReportView.setFirstName(rs.getString("firstName"));
            adminReportView.setLastName(rs.getString("lastName"));
            return adminReportView;
        } catch (SQLException e) {
            throw new MySQLException("cannot map AdminReportView",e);
        }
    }


}
