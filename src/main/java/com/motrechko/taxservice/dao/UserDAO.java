package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.model.User;
import com.motrechko.taxservice.exception.MySQLException;

import java.util.List;
import java.util.Optional;

/**
 Interface representing the data access object (DAO) for the User entity.
 */
public interface UserDAO {
    /**
     Creates a new user record in the database.
     @param user the User object representing the user to be created
     @return User if the user is successfully created
     @throws MySQLException if an error occurs while accessing the database
     */
    User create(User user) throws MySQLException;
    /**
     Updates an existing user record in the database.
     @throws MySQLException if an error occurs while accessing the database
     */
    void update(User user) throws MySQLException;
    /**
     Deletes an existing user record from the database.
     @throws MySQLException if an error occurs while accessing the database
     */
    void delete(int id) throws MySQLException;
    /**
     Retrieves a list of all user records from the database.
     @return a List of User objects representing all users in the database
     @throws MySQLException if an error occurs while accessing the database
     */
    List<User> getAllUsers() throws MySQLException;
    /**
     Retrieves a user record from the database by email.
     @param email the email address of the user to retrieve
     @return an {@link Optional} object containing a {@link User} object representing the retrieved user if it exists,
     otherwise an empty {@link Optional} object
     @throws MySQLException if an error occurs while accessing the database
     */
    Optional<User> getByEmail(String email) throws MySQLException;
    Optional<User> getById(int idUser) throws MySQLException;

}