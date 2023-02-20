package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.model.User;
import com.motrechko.taxservice.dao.mysql.MySQLException;

import java.util.List;

public interface UserDAO {
    boolean create(User user)  throws MySQLException;
    void update()  throws MySQLException;
    void delete()  throws MySQLException;
    List<User> getAllUsers() throws MySQLException;
    User getByEmail(String login)  throws MySQLException;
    String hashPassword(String password);
}
