package com.motrechko.taxservice.commands;


import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.dao.DAOFactory;
import com.motrechko.taxservice.enums.Target;
import com.motrechko.taxservice.enums.UserType;
import com.motrechko.taxservice.exception.AuthenticationException;
import com.motrechko.taxservice.exception.InspectorException;
import com.motrechko.taxservice.exception.UserException;
import com.motrechko.taxservice.model.Inspector;
import com.motrechko.taxservice.model.User;
import com.motrechko.taxservice.service.AuthService;
import com.motrechko.taxservice.service.InspectorService;
import com.motrechko.taxservice.service.UserService;
import jakarta.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * The LoginCommand handles user authentication and determines the user type.
 */
public class LoginCommand extends FrontCommand {

    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    /**
     * Authenticates user login and sets current user to the session.
     *
     * @return CommandResponse for the user type or ERROR if authentication failed.
     * @throws ServletException if an error occurs during the servlet execution.
     * @throws IOException if an error occurs during the input/output operations.
     */
    @Override
    public CommandResponse process() throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AuthService authService = new AuthService();

        try {
            if (authService.authenticate(email, password)) {
                if (authService.isUserType(email, UserType.User)) {
                    UserService userService = new UserService();
                    User user = userService.getUserByEmail(email);
                    request.getSession().setAttribute("currentUser", user);
                    logger.info("User with email {} successfully logged in.", email);
                    return new CommandResponse(Target.JSP, FrontConstant.PROFILE_USER);
                } else if (authService.isUserType(email, UserType.Inspector)) {
                    InspectorService inspectorService = new InspectorService();
                    Inspector inspector = inspectorService.getInspectorByEmail(email);
                    request.getSession().setAttribute("currentUser", inspector);
                    logger.info("Inspector with email {} successfully logged in.", email);
                    return new CommandResponse(Target.JSP, FrontConstant.REPORTS_ADMIN);
                }
            }
        } catch (AuthenticationException | UserException | InspectorException | IllegalArgumentException e) {
            logger.error("An error occurred during user authentication: {}" , e.getMessage(), e);
            request.getSession().setAttribute("errorMessage",e.getMessage());
            return new CommandResponse(Target.JSP, FrontConstant.ERROR);
        }
        logger.warn("User with email {} failed to log in.", email);
        return new CommandResponse(Target.JSP, FrontConstant.ERROR);
    }
}

