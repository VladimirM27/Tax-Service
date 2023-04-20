package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.model.Entity;
import com.motrechko.taxservice.model.ReportType;

import java.util.List;

/**
 The ReportTypeDAO interface provides methods for retrieving ReportType objects from a database.
 */
public interface ReportTypeDAO {
    /**
     Retrieves a ReportType object by its ID.
     @param id the ID of the ReportType to retrieve
     @return the ReportType object with the specified ID
     @throws MySQLException if there is an error accessing the database
     */
    ReportType getReportTypeById(int id) throws MySQLException;
    /**
     Retrieves a ReportType object by its name.
     @param name the name of the ReportType to retrieve
     @return the ReportType object with the specified name
     @throws MySQLException if there is an error accessing the database
     */
    ReportType getReportTypeByName(String name) throws MySQLException;
    /**
     Retrieves a list of ReportType objects that are associated with a specific entity.
     @param entity the entity for which to retrieve ReportType objects
     @return a list of ReportType objects associated with the specified entity
     @throws MySQLException if there is an error accessing the database
     */
    List<ReportType> getReportTypeByEntity(Entity entity) throws MySQLException;
}
