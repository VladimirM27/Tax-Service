package com.motrechko.taxservice.service;

import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.exception.UserException;
import com.motrechko.taxservice.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;


public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    /**
     Retrieves a user record by email from the data storage system.
     @param email the email address of the user to retrieve
     @return the User object representing the retrieved user
     @throws UserException if the email is empty or null, or if an error occurs while accessing the database
     */
    public User getUserByEmail(String email) throws UserException {
        if(email == null || email.isEmpty())
            throw new IllegalArgumentException("Email cannot be empty");
        try {
            Optional<User> user = DAOFactory.getInstance().getUserDAO().getByEmail(email);
            if(user.isPresent())
                return user.get();
            else
                throw new UserException("User was null");
        } catch (MySQLException e) {
            LOGGER.error("Error while retrieving user with email {}: {}", email, e.getMessage());
            throw new UserException("Error while retrieving user with email " + email, e);
        }
    }
}
