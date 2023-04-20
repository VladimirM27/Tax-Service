package com.motrechko.taxservice.service;

import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.exception.InspectorException;
import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.model.Inspector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class InspectorService {

    private static final Logger LOGGER = LogManager.getLogger(InspectorService.class);

    /**
     Retrieves an Inspector object by email from the data storage system.
     @param email the email of the Inspector to retrieve
     @return the Inspector object if found, otherwise throws an InspectorException
     @throws IllegalArgumentException if email is null or empty
     @throws InspectorException if an error occurs while accessing the database or if Inspector is not found
     */
    public Inspector getInspectorByEmail(String email) throws InspectorException {
        if(email == null || email.isEmpty())
            throw new IllegalArgumentException("Email cannot be empty");
        try {
            Optional<Inspector> inspector =  DAOFactory.getInstance().getInspectorDAO().getByEmail(email);
            if(inspector.isPresent())
                return inspector.get();
            else
                throw new InspectorException("Inspector was null");
        } catch (MySQLException e) {
            LOGGER.error("Error while retrieving user with email {}: {}", email, e.getMessage());
            throw new InspectorException("Error while retrieving user with email " + email, e);
        }
    }
}
