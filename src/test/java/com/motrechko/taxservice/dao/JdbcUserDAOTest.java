package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.dao.impl.MySQLException;
import com.motrechko.taxservice.model.User;
import com.motrechko.taxservice.utils.UserFactory;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JdbcUserDAOTest {
    private UserDAO userDAO;

    @BeforeEach
    public void setup(){
        userDAO = DAOFactory.getInstance().getUserDAO();
    }

    @Test
    void should_CorrectAddNewUser_When_CorrectInput() throws MySQLException {
        User user = UserFactory.createRandomUser();
        assertTrue(userDAO.create(user));
        User createdUser = userDAO.getByEmail(user.getEmail());
        assertNotNull(createdUser);
        assertEquals(createdUser.getFirstName(), user.getFirstName());
        assertEquals(createdUser.getLastName(), user.getLastName());
        assertEquals(createdUser.getRole(), user.getRole());
        assertEquals(createdUser.getPassword(), user.getPassword());
        assertEquals(createdUser.getEmail(), user.getEmail());
        assertEquals(createdUser.getTIN(), user.getTIN());
        assertEquals(createdUser.getCity(), user.getCity());
        userDAO.delete(user.getId());
    }

    @Test
    void should_DontAddUser_When_InputIsNull()  {
        Executable executable = () -> userDAO.create(null);
        assertThrows(MySQLException.class, executable);
    }

    @Test
    void should_DontAddUser_When_OneParameter_IsNull() throws MySQLException {
        User user = UserFactory.createRandomUser();
        user.setEmail(null);

        Executable executable = () -> userDAO.create(user);

        assertThrows(MySQLException.class, executable);
    }

    @Test
    void should_CorrectFindByEmail_When_CorrectInput() throws MySQLException {
        User user = UserFactory.createRandomUser();
        userDAO.create(user);
        User findUser = userDAO.getByEmail(user.getEmail());
        assertNotNull(findUser);
        assertEquals(user.getEmail(), findUser.getEmail());
        userDAO.delete(user.getId());
    }

    @Test
    void should_CorrectDeleteUser_When_CorrectInput() throws MySQLException {
        User user = UserFactory.createRandomUser();
        assertTrue(userDAO.create(user));
        assertNotNull(userDAO.getByEmail(user.getEmail()));
        int userId = user.getId();
        userDAO.delete(userId);
        Executable executable = ()-> userDAO.getByEmail(user.getEmail());
        assertThrows(MySQLException.class, executable );
    }

    @Test
    void should_CorrectFindAllUsers() throws MySQLException {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User newUser = UserFactory.createRandomUser();
            userDAO.create(newUser);
            userList.add(newUser);
        }

        List<User> getAllUsers = userDAO.getAllUsers();
        assertNotNull(getAllUsers);
        assertEquals(getAllUsers.size(), userList.size());
        assertEquals(userList, getAllUsers);

        for(User user: userList)
            userDAO.delete(user.getId());
    }

    @Test
    void should_CorrectUpdate_When_CorrectInput() throws MySQLException {
        User user = UserFactory.createRandomUser();
        assertTrue(userDAO.create(user));
        String newEmail = "newTestemail@gmail.com";
        user.setEmail(newEmail);
        userDAO.update(user);
        User updatedUser = userDAO.getByEmail(newEmail);
        assertNotNull(updatedUser);
        assertEquals(user.getId(), updatedUser.getId());
        assertEquals(user.getCompany(), updatedUser.getCompany());
        assertEquals(updatedUser.getFirstName(), user.getFirstName());
        assertEquals(updatedUser.getLastName(), user.getLastName());
        assertEquals(updatedUser.getRole(), user.getRole());
        assertEquals(updatedUser.getPassword(), user.getPassword());
        assertEquals(updatedUser.getEmail(), user.getEmail());
        assertEquals(updatedUser.getTIN(), user.getTIN());
        assertEquals(updatedUser.getCity(), user.getCity());
        userDAO.delete(user.getId());
    }

    @Test
    void should_DontCorrectUpdate_When_IncorrectInput() throws MySQLException {
        User user = UserFactory.createRandomUser();
        assertTrue(userDAO.create(user));
        user.setEmail(null);
        Executable executable = () -> userDAO.update(user);
        assertThrows(MySQLException.class, executable);
        userDAO.delete(user.getId());
    }
}
