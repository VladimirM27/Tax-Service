package com.epam.motrechko.db.mysql;

import com.epam.motrechko.db.dao.UserDAO;
import com.epam.motrechko.db.entity.User;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
    public User getByLogin(String login) throws MySQLException {
        try (Connection connection = MySQLConnectionPool.getInstance().getConnection(true);
             PreparedStatement statement = connection.prepareStatement(MySQLQuery.SELECT_USER_BY_LOGIN)){
            statement.setString(1,login);
            ResultSet set = statement.executeQuery();
            set.next();
            return mapUser(set);
        } catch (SQLException e){
            throw new MySQLException("Cannot get user", e);
        }
    }

    @Override
    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update((password + "dd").getBytes());
            byte[] hash = digest.digest();
            String value = Hex.encodeHexString(hash);
            return value;
        } catch (NoSuchAlgorithmException e){
                throw new RuntimeException(e);
        }
    }
}
