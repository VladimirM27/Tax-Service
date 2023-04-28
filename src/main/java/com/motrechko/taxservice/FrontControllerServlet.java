package com.motrechko.taxservice;

import com.motrechko.taxservice.commands.CommandResponse;
import com.motrechko.taxservice.commands.FrontCommand;
import com.motrechko.taxservice.commands.UnknownCommand;
import com.motrechko.taxservice.enums.Target;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;

@WebServlet("/controller")
@MultipartConfig
public class FrontControllerServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(FrontControllerServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontCommand command = getCommand(req);
        command.init(getServletContext(),req,resp);
        logger.info(()-> "New GET request" + command.getClass().getName());
        CommandResponse response = command.process();
        String path = getPath(response.path(),response.target());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(path);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontCommand command = getCommand(req);
        command.init(getServletContext(),req,resp);
        logger.info(()-> "New POST request" + command.getClass().getName());
        CommandResponse response = command.process();
        String path = getPath(response.path(),response.target());
        resp.sendRedirect(req.getContextPath() + path);
    }


    private String getPath(String path, Target target) {
        return switch (target) {
            case JSP -> getJSPPath(path);
            case COMMAND -> getCommandPath(path);
            default -> getJSPPath(FrontConstant.ERROR);
        };
    }


    private String getJSPPath(String target)  {
        return String.format("/jsp/%s.jsp",target);
    }
    private String getCommandPath(String target)  {
        return String.format("/controller?command=%s",target);
    }

    private FrontCommand getCommand(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commandName == null) {
            return new UnknownCommand();
        }
        try {
            Class<?> type = Class.forName(String.format("com.motrechko.taxservice.commands.%sCommand", commandName));
            return type.asSubclass(FrontCommand.class).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            return new UnknownCommand();
        }
    }
}
