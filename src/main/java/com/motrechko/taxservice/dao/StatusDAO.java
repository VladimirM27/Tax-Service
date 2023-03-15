package com.motrechko.taxservice.dao;


import com.motrechko.taxservice.dao.exception.MySQLException;
import com.motrechko.taxservice.model.Status;

public interface StatusDAO {
    boolean create (Status status) throws MySQLException;
    void delete(Status status) throws MySQLException;

}
