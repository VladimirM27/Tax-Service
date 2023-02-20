package com.motrechko.taxservice.commands;


import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.dao.UserDAO;
import com.motrechko.taxservice.model.User;
import com.motrechko.taxservice.dao.impl.MySQLException;
import com.motrechko.taxservice.enums.Target;
import jakarta.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class LoginCommand extends FrontCommand{
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    @Override
    public CommandResponse process() throws ServletException, IOException {
        String login = request.getParameter("email");
        String password = request.getParameter("password");


        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            User user = userDAO.getByEmail(login);
            if(user!= null && user.getPassword().equals(userDAO.hashPassword(password))){
                request.getSession().setAttribute("currentUser",user);
                if(user.getRole().equals("user")){
                    logger.info("Logged new user");
                    return new CommandResponse(Target.JSP,FrontConstant.PROFILE_USER);

                }else if(user.getRole().equals("inspector")){
                    logger.info("Logged new inspector");
                    return new CommandResponse(Target.JSP,FrontConstant.REPORTS_ADMIN);
                }
            }
        } catch (MySQLException e) {
            logger.error("user login error:" , e);
        }
        return new CommandResponse(Target.JSP,FrontConstant.ERROR);
    }
}
