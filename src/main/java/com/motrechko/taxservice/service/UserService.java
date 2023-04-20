package com.motrechko.taxservice.service;

import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.UserDAO;
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
            return user.orElse(null);
        } catch (MySQLException e) {
            LOGGER.error("Error while retrieving user with email {}: {}", email, e.getMessage());
            throw new UserException("Error while retrieving user with email " + email, e);
        }
    }

    public User getUserById(int idUser) throws UserException {
        if(idUser < 0 )
            throw new IllegalArgumentException("Id cannot be less than 0");
        try {
            Optional<User> user = DAOFactory.getInstance().getUserDAO().getById(idUser);
            if(user.isPresent())
                return user.get();
            else
                throw new UserException("User was null");
        } catch (MySQLException| UserException e) {
            LOGGER.error("Error while retrieving user with id {}: {}", idUser, e.getMessage());
            throw new UserException("Error while retrieving user with id " + idUser, e);
        }
    }

    /**

     Creates a new user in the database.
     @return {@link User} if User successfully created
     @param user the user to be created
     @throws UserException if there is an error creating the user
     */
    public User create(User user) throws UserException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            return userDAO.create(user);
        } catch (MySQLException e) {
            String errorMessage = "Error creating user with email address: " + user.getEmail();
            LOGGER.error(errorMessage, e);
            throw new UserException(errorMessage, e);
        }
    }

}
