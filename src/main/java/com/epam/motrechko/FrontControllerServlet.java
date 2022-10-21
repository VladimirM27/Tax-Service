package com.epam.motrechko;

import com.epam.motrechko.commands.FrontCommand;
import com.epam.motrechko.commands.UnknownCommand;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;

@WebServlet("/controller")
public class FrontControllerServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(FrontControllerServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontCommand command = getCommand(req);
        command.init(getServletContext(),req,resp);
//        if (logger.isTraceEnabled()) {
//            logger.trace("GET request");
//        }


        command.process();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontCommand command = getCommand(req);
        command.init(getServletContext(),req,resp);
        logger.info("New POST request" + command.getClass().getName());
        command.process();
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
