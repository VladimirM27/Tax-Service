package com.epam.motrechko.commands;

import com.epam.motrechko.FrontControllerServlet;
import com.epam.motrechko.db.dao.DAOFactory;
import com.epam.motrechko.db.dao.UserDAO;
import com.epam.motrechko.db.entity.User;
import com.epam.motrechko.db.mysql.MySQLException;
import jakarta.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class LoginCommand extends FrontCommand{
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    @Override
    public void process() throws ServletException, IOException {
        String login = request.getParameter("email");
        String password = request.getParameter("password");


        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            User user = userDAO.getByEmail(login);
            if(user!= null && user.getPassword().equals(userDAO.hashPassword(password))){
                // todo session set atribute
                logger.info("Logged new user");
            }
        } catch (MySQLException e) {
            logger.error("user login error:" + e);
        }
    }
}
