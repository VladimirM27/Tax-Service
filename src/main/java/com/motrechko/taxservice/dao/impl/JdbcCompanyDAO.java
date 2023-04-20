package com.motrechko.taxservice.dao.impl;

import com.motrechko.taxservice.dao.CompanyDAO;
import com.motrechko.taxservice.dao.ConnectionFactory;
import com.motrechko.taxservice.dao.queries.CompanyQueries;
import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.exception.UserException;
import com.motrechko.taxservice.model.Company;
import com.motrechko.taxservice.model.User;
import com.motrechko.taxservice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCompanyDAO implements CompanyDAO {

    private static final Logger logger = LogManager.getLogger(JdbcCompanyDAO.class);
    @Override
    public List<Company> getAll() throws MySQLException {
        try(Connection connection = ConnectionFactory.getConnection(true);
            Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery(CompanyQueries.GET_ALL);
            List<Company> companies = new ArrayList<>();
            while (rs.next()){
                Optional<Company> company = mapCompany(rs);
                company.ifPresent(companies::add);
            }
            return companies;
        }catch (SQLException| UserException e){
            logger.warn("Failed to get a list of all company's", e);
            throw new MySQLException("cannot select all company's" , e);
        }
    }

    private Optional<Company> mapCompany(ResultSet rs) throws SQLException, UserException {
        Company company = new Company();
        company.setId(rs.getInt("idcompany"));
        UserService userService = new UserService();
        User user = userService.getUserById(rs.getInt("idUser"));
        company.setUser(user);
        company.setCountEmployee(rs.getInt("count_employee"));
        company.setCity(rs.getString("city"));
        company.setStreet(rs.getString("street"));
        company.setNumberOfBuilding(rs.getString("number_of_building"));
        company.setName(rs.getString("name"));
        company.setDescription(rs.getString("description"));
        return Optional.of(company);

    }

    @Override
    public Optional<Company> getCompanyByUser(int userId) {
        return Optional.empty();
    }
}
