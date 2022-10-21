package com.epam.motrechko.db.mysql;

import com.epam.motrechko.commands.RegistrationCommand;
import com.epam.motrechko.db.dao.UserDAO;
import com.epam.motrechko.db.entity.User;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class MySQLUserDAO implements UserDAO {
    private final static Logger logger = LogManager.getLogger(MySQLUserDAO.class);
    @Override
    public boolean create(User user) throws MySQLException {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getInstance().getConnection(false);
            addUser(connection,user);
            connection.commit();
            return true;
        } catch (SQLException | MySQLException e){
            MySQLManager.rollback(connection);
            logger.warn("Cannot add new user"  + e);
            throw new MySQLException("Cannot add new User",e);
        }
        finally {
            MySQLManager.close(connection);
        }
    }



    private void addUser(Connection connection, User user) throws MySQLException {
        try (PreparedStatement statement = connection.prepareStatement(MySQLQuery.INSERT_INTO_USER,Statement.RETURN_GENERATED_KEYS)){
            int i = 0;
            statement.setString(++i,user.getEmail());
            statement.setString(++i,user.getPassword());
            statement.setString(++i,user.getEntity());
            statement.setString(++i,user.getRole());
            statement.setString(++i,user.getFirstName());
            statement.setString(++i,user.getLastName());
            int c = statement.executeUpdate();
            if(c> 0){
                try(ResultSet set = statement.getGeneratedKeys()){
                    if(set.next()){
                        user.setId(set.getInt(1));
                    }
                }

            }
        } catch (SQLException e){
            throw new MySQLException("Cannot insert new user" , e);
        }
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
        user.setEntity(rs.getString("login"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        user.setId(rs.getInt("idUsers"));
        return user;
    }

    @Override
    public User getByEmail(String email) throws MySQLException {
        try (Connection connection = MySQLConnectionPool.getInstance().getConnection(true);
             PreparedStatement statement = connection.prepareStatement(MySQLQuery.SELECT_USER_BY_EMAIL)){
            statement.setString(1,email);
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
