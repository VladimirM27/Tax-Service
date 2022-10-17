package com.epam.motrechko.db.dao;

import com.epam.motrechko.db.entity.User;
import com.epam.motrechko.db.mysql.MySQLException;

import java.util.List;

public interface UserDAO {
    boolean create();
    void update();
    void delete();
    List<User> getAllUsers() throws MySQLException;
    User getByLogin(String login);
    String hashPassword(String password);
}
