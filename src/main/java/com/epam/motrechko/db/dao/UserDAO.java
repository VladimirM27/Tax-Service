package com.epam.motrechko.db.dao;

import com.epam.motrechko.db.entity.User;
import com.epam.motrechko.db.mysql.MySQLException;

import java.util.List;

public interface UserDAO {
    boolean create(User user)  throws MySQLException;
    void update()  throws MySQLException;
    void delete()  throws MySQLException;
    List<User> getAllUsers() throws MySQLException;
    User getByEmail(String login)  throws MySQLException;
    String hashPassword(String password);
}
