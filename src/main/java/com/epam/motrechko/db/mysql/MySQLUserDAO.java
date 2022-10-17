package com.epam.motrechko.db.mysql;

import com.epam.motrechko.db.dao.UserDAO;
import com.epam.motrechko.db.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MySQLUserDAO implements UserDAO {
    @Override
    public boolean create() {
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public List<User> getAllUsers() throws MySQLException {
        try(Connection connection = MySQLConnectionPool.getInstance().getConnection(true)){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(MySQLQuery.SELECT_ALL_USERS);
            List<User> users = new ArrayList<>();
            while (rs.next()){
                User user = mapUser(rs);
                users.add(user);
            }
            return users;
        }catch (SQLException e){
            throw new MySQLException("cannot select all users" , e);
        }
    }

    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setLogin(rs.getString("login"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        user.setId(rs.getInt("idUsers"));
        return user;
    }

    @Override
    public User getByLogin(String login) {
        return null;
    }

    @Override
    public String hashPassword(String password) {
        return null;
    }
}
