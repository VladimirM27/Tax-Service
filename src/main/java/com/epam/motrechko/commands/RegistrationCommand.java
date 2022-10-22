package com.epam.motrechko.commands;

import com.epam.motrechko.db.dao.DAOFactory;
import com.epam.motrechko.db.dao.UserDAO;
import com.epam.motrechko.db.entity.User;
import com.epam.motrechko.db.mysql.MySQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class RegistrationCommand extends FrontCommand{

    private final static Logger logger = LogManager.getLogger(RegistrationCommand.class);
    private  UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
    @Override
    public void process() throws ServletException, IOException {

        try {
            User user = getNewUserFromRequest(request);
            userDAO.create(user);
        }catch (MySQLException e) {
            logger.warn("Cannot register new user: " , e);
            throw new ServletException(e);
        }
    }

    private User getNewUserFromRequest(ServletRequest request){
        String email = request.getParameter("emailAddress");
        String password = request.getParameter("secondPassword");
        String hashPassword = userDAO.hashPassword(password);
        String entity = request.getParameter("entity");
        String role = "user";
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashPassword);
        user.setEntity(entity);
        user.setRole(role);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }
}
