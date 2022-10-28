package com.epam.motrechko;

import com.epam.motrechko.commands.FrontCommand;
import com.epam.motrechko.commands.UnknownCommand;
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
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(getJSPPath(command.process()));
        dispatcher.forward(req, resp);
    }

    private String getJSPPath(String target)  {
        return String.format("/jsp/%s.jsp",target);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontCommand command = getCommand(req);
        command.init(getServletContext(),req,resp);
        logger.info(()-> "New POST request" + command.getClass().getName());

        resp.sendRedirect(req.getContextPath() + getJSPPath(command.process()));
    }

    private FrontCommand getCommand(HttpServletRequest request){
        try {
            Class type = Class.forName(String.format(
                    "com.epam.motrechko.commands.%sCommand",
                    request.getParameter("command")));
            return (FrontCommand) type
                    .asSubclass(FrontCommand.class)
                    .newInstance();
        } catch (Exception e){
            return new UnknownCommand();
        }
    }
}
