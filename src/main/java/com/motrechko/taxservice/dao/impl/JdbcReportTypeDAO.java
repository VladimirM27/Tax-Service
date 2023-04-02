package com.motrechko.taxservice.dao.impl;

import com.motrechko.taxservice.dao.ConnectionFactory;
import com.motrechko.taxservice.dao.ReportTypeDAO;
import com.motrechko.taxservice.dao.exception.MySQLException;
import com.motrechko.taxservice.dao.queries.ReportTypeQueries;
import com.motrechko.taxservice.model.Entity;
import com.motrechko.taxservice.model.ReportType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcReportTypeDAO implements ReportTypeDAO {

    private static final Logger logger = LogManager.getLogger(JdbcReportTypeDAO.class);
    @Override
    public ReportType getReportTypeById(int id) throws MySQLException {
        try (Connection connection = ConnectionFactory.getConnection(true);
             PreparedStatement statement = connection.prepareStatement(ReportTypeQueries.SELECT_TYPE_BY_ID)){
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            set.next();
            logger.info("a report type with id {} was found ", id);
            return mapReportType(set);
        } catch (SQLException e){
            logger.warn("Failed to get a report type", e);
            throw new MySQLException("Failed to get a report type", e);
        }
    }

    private ReportType mapReportType(ResultSet set) throws SQLException {
        ReportType reportType = new ReportType();
        reportType.setReportTypeId(set.getInt("idreportType"));
        reportType.setType(set.getString("type"));
        reportType.setEntity(set.getString("entity"));
        return reportType;
    }

    @Override
    public ReportType getReportTypeByName(String name) throws MySQLException {
        try (Connection connection = ConnectionFactory.getConnection(true);
             PreparedStatement statement = connection.prepareStatement(ReportTypeQueries.SELECT_TYPE_BY_NAME)){
            statement.setString(1,name);
            ResultSet set = statement.executeQuery();
            set.next();
            logger.info("a report type with name {} was found ", name);
            return mapReportType(set);
        } catch (SQLException e){
            logger.warn("Failed to get a report type", e);
            throw new MySQLException("Failed to get a report type", e);
        }
    }

    @Override
    public List<ReportType> getReportTypeByEntity(Entity entity) throws MySQLException {
        try(Connection connection = ConnectionFactory.getConnection(true);
            PreparedStatement st = connection.prepareStatement(ReportTypeQueries.GET_ALL_TYPES_BY_INDIVIDUAL)){
            st.setString(1,entity.getValue());
            ResultSet rs = st.executeQuery();
            List<ReportType> reportTypes = new ArrayList<>();
            while (rs.next()){
                ReportType reportType = mapReportType(rs);
                reportTypes.add(reportType);
            }
            return reportTypes;
        }catch (SQLException e){
            logger.warn("Failed to get a list of types", e);
            throw new MySQLException("cannot select report types" , e);
        }
    }
}
