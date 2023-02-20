package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.model.User;
import com.motrechko.taxservice.dao.impl.MySQLException;

import java.util.List;

/**
 Interface representing the data access object (DAO) for the User entity.
 */
public interface UserDAO {
    /**
     Creates a new user record in the database.
     @param user the User object representing the user to be created
     @return true if the user is successfully created, false otherwise
     @throws MySQLException if an error occurs while accessing the database
     */
    boolean create(User user) throws MySQLException;
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
     @return a User object representing the retrieved user, or null if the user is not found
     @throws MySQLException if an error occurs while accessing the database
     */
    User getByEmail(String email) throws MySQLException;
    /**
     Hashes a password using a secure one-way hashing algorithm.
     @param password the password to be hashed
     @return a String representing the hashed password
     */
    String hashPassword(String password);

}