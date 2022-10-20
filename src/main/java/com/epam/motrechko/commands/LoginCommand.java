package com.epam.motrechko.commands;

import com.epam.motrechko.db.dao.DAOFactory;
import com.epam.motrechko.db.dao.UserDAO;
import com.epam.motrechko.db.entity.User;
import com.epam.motrechko.db.mysql.MySQLException;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class LoginCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");


        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            User user = userDAO.getByLogin(login);
            if(user!= null && user.getPassword().equals(userDAO.hashPassword(password))){
                // todo session set atribute
                System.out.println("success ");
            }
        } catch (MySQLException e) {
            throw new RuntimeException(e);
        }
    }
}
