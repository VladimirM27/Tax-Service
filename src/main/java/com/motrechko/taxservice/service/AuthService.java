package com.motrechko.taxservice.service;

import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.enums.UserType;
import com.motrechko.taxservice.exception.AuthenticationException;
import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.model.Inspector;
import com.motrechko.taxservice.model.User;
import com.motrechko.taxservice.utils.PasswordUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * Provides authentication services for users and inspectors.
 */
public class AuthService {

    private static final Logger LOGGER = LogManager.getLogger(AuthService.class);

    /**
     * Authenticates a user or inspector with the given email and password.
     *
     * @param email    the email address of the user or inspector
     * @param password the password of the user or inspector
     * @return true if the email and password match a user or inspector in the database; false otherwise
     * @throws AuthenticationException if there is an error during authentication
     */
    public boolean authenticate(String email, String password) throws AuthenticationException {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Email and password cannot be empty");
        }

        try {
            Optional<User> user = DAOFactory.getInstance().getUserDAO().getByEmail(email);
            Optional<Inspector> inspector = DAOFactory.getInstance().getInspectorDAO().getByEmail(email);

            if ((user.isPresent() && user.get().getPassword().equals(PasswordUtils.hashPassword(password))) ||
                    (inspector.isPresent() && inspector.get().getPassword().equals(PasswordUtils.hashPassword(password)))) {
                LOGGER.info("User or inspector with email {} successfully authenticated", email);
                return true;
            }
        } catch (MySQLException e) {
            LOGGER.error("Error during authentication for email {}: {}", email, e.getMessage());
            throw new AuthenticationException("Unable to authenticate user", e);
        }

        LOGGER.info("User or inspector with email {} failed to authenticate", email);
        return false;
    }


    /**
     Checks if the user with the given email is of the specified user type.
     @param email the email of the user to check
     @param userType the user type to check against
     @return true if the user is of the specified type, false otherwise
     @throws AuthenticationException if there is an error while accessing the database
     */
    public boolean isUserType(String email, UserType userType) throws AuthenticationException {
        try {
            if (userType == UserType.Inspector) {
                boolean isInspector = DAOFactory.getInstance().getInspectorDAO().getByEmail(email).isPresent();
                LOGGER.info("Checking if user with email {} is an inspector: {}", email, isInspector);
                return isInspector;
            } else if (userType == UserType.User) {
                boolean isUser = DAOFactory.getInstance().getUserDAO().getByEmail(email).isPresent();
                LOGGER.info("Checking if user with email {} is a user: {}", email, isUser);
                return isUser;
            } else {
                throw new IllegalArgumentException("Invalid user type: " + userType);
            }
        } catch (MySQLException e) {
            LOGGER.error("Error during authentication for email {}: {}", email, e.getMessage());
            throw new AuthenticationException("Unable to authenticate user", e);
        }
    }


}

