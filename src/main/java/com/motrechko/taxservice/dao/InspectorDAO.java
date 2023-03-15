package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.dao.exception.MySQLException;
import com.motrechko.taxservice.model.Inspector;

/**
 The InspectorDAO interface provides methods for accessing and manipulating Inspector data in a data storage system.
 */
public interface InspectorDAO {
    /**
     Creates a new Inspector record in the data storage system.
     @param inspector the Inspector object to be created
     @return true if the creation is successful, false otherwise
     */
    boolean create(Inspector inspector) throws MySQLException;
    /**
     Retrieves an Inspector record by email from the data storage system.
     @param email the email of the Inspector to retrieve
     @return the Inspector object if found, null otherwise
     */
    Inspector getByEmail(String email) throws MySQLException;

    void delete (int id) throws MySQLException;
}
