package com.motrechko.taxservice.dao;

import com.motrechko.taxservice.exception.MySQLException;
import com.motrechko.taxservice.model.User;
import com.motrechko.taxservice.utils.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Executable executable = () -> userDAO.create(user);
        assertDoesNotThrow(executable);
        Optional<User> createdUser = userDAO.getByEmail(user.getEmail());
        assertTrue(createdUser.isPresent());
        assertEquals(createdUser.get().getFirstName(), user.getFirstName());
        assertEquals(createdUser.get().getLastName(), user.getLastName());
        assertEquals(createdUser.get().getPassword(), user.getPassword());
        assertEquals(createdUser.get().getEmail(), user.getEmail());
        assertEquals(createdUser.get().getTIN(), user.getTIN());
        assertEquals(createdUser.get().getCity(), user.getCity());
        userDAO.delete(user.getId());
    }

    @Test
    void should_DontAddUser_When_InputIsNull()  {
        Executable executable = () -> userDAO.create(null);
        assertThrows(MySQLException.class, executable);
    }

    @Test
    void should_DontAddUser_When_OneParameter_IsNull()  {
        User user = UserFactory.createRandomUser();
        user.setEmail(null);

        Executable executable = () -> userDAO.create(user);

        assertThrows(MySQLException.class, executable);
    }

    @Test
    void should_CorrectFindByEmail_When_CorrectInput() throws MySQLException {
        User user = UserFactory.createRandomUser();
        userDAO.create(user);
        Optional<User> findUser = userDAO.getByEmail(user.getEmail());
        assertTrue(findUser.isPresent());
        assertEquals(user.getEmail(), findUser.get().getEmail());
        userDAO.delete(user.getId());
    }

    @Test
    void should_CorrectDeleteUser_When_CorrectInput() throws MySQLException {
        User user = UserFactory.createRandomUser();
        Executable executable = () -> userDAO.create(user);
        assertDoesNotThrow(executable);
        assertTrue(userDAO.getByEmail(user.getEmail()).isPresent());
        int userId = user.getId();
        userDAO.delete(userId);
        assertFalse(userDAO.getByEmail(user.getEmail()).isPresent());
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
        Executable executable = () -> userDAO.create(user);
        assertDoesNotThrow(executable);
        String newEmail = "newTestemail@gmail.com";
        user.setEmail(newEmail);
        userDAO.update(user);
        Optional<User> updatedUser = userDAO.getByEmail(newEmail);
        assertTrue(updatedUser.isPresent());
        assertEquals(user.getId(), updatedUser.get().getId());
        assertEquals(updatedUser.get().getFirstName(), user.getFirstName());
        assertEquals(updatedUser.get().getLastName(), user.getLastName());
        assertEquals(updatedUser.get().getPassword(), user.getPassword());
        assertEquals(updatedUser.get().getEmail(), user.getEmail());
        assertEquals(updatedUser.get().getTIN(), user.getTIN());
        assertEquals(updatedUser.get().getCity(), user.getCity());
        userDAO.delete(user.getId());
    }

    @Test
    void should_DontCorrectUpdate_When_IncorrectInput() throws MySQLException {
        User user = UserFactory.createRandomUser();
        Executable createUser = () -> userDAO.create(user);
        assertDoesNotThrow(createUser);
        user.setEmail(null);
        Executable executable = () -> userDAO.update(user);
        assertThrows(MySQLException.class, executable);
        userDAO.delete(user.getId());
    }
}
