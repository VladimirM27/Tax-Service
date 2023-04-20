package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyDAO {
    List<Company> getAll() throws MySQLException;
    Optional<Company> getCompanyByUser(int userId);
}
