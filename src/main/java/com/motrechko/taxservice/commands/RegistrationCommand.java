package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.exception.UserException;
import com.motrechko.taxservice.model.User;
import com.motrechko.taxservice.enums.Target;
import com.motrechko.taxservice.service.UserService;
import com.motrechko.taxservice.utils.PasswordUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class RegistrationCommand extends FrontCommand{
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);
    /**
     Processes the user registration request and returns the appropriate CommandResponse.
     @return the CommandResponse for the registration request.
     @throws ServletException if an error occurs during request processing.
     @throws IOException if an error occurs while writing the response.
     */
    @Override
    public CommandResponse process() throws ServletException, IOException {
        UserService userService = new UserService();
        try {
            User user = parseUserFromRequest(request);
            if (userService.getUserByEmail(user.getEmail()) != null) {
                logger.warn("Cannot register new user: user with email address {}" , user.getEmail() + " already exists.");
                return new CommandResponse(Target.JSP,FrontConstant.ERROR);
            }
            User userWithId = userService.create(user);
            logger.info("A new user is registered: {}" , user.getEmail());
            request.getSession().setAttribute("currentUser",userWithId);
            return new CommandResponse(Target.JSP,FrontConstant.PROFILE_USER);
        } catch (UserException e) {
            logger.warn("Cannot register new user: {}" , e.getMessage(), e);
            return new CommandResponse(Target.JSP,FrontConstant.ERROR);
        }
    }
    /**
     Parses user registration information from the given request and returns a new User object.
     @param request the ServletRequest containing user registration information.
     @return a new User object representing the user's registration information.
     @throws NumberFormatException if any of the numeric fields are not valid integers.
     */
    private User parseUserFromRequest(ServletRequest request){
        User user = new User();
        user.setEmail(request.getParameter("emailAddress"));
        String password = PasswordUtils.hashPassword(request.getParameter("secondPassword"));
        user.setPassword(password);
        user.setEntity(Integer.parseInt(request.getParameter("entity")));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setTIN(Long.parseLong(request.getParameter("TIN")));
        user.setCity(request.getParameter("city"));
        user.setStreet(request.getParameter("street"));
        user.setNumberOfBuilding(request.getParameter("numberOfBuilding"));
        return user;
    }
}
