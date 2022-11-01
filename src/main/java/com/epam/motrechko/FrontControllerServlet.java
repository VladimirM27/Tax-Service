package com.epam.motrechko;

import com.epam.motrechko.commands.CommandResponse;
import com.epam.motrechko.commands.FrontCommand;
import com.epam.motrechko.commands.UnknownCommand;
import com.epam.motrechko.enums.Target;
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
        String path = getPath(response.getPath(),response.getTarget());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(path);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontCommand command = getCommand(req);
        command.init(getServletContext(),req,resp);
        logger.info(()-> "New POST request" + command.getClass().getName());
        CommandResponse response = command.process();
        String path = getPath(response.getPath(),response.getTarget());
        resp.sendRedirect(req.getContextPath() + path);
    }

    private String getPath(String path, Target target) {
        if(target == Target.COMMAND)
            return getCommandPath(path);
        else if(target == Target.JSP)
            return getJSPPath(path);
        else return getJSPPath(FrontConstant.ERROR);
    }

    private String getJSPPath(String target)  {
        return String.format("/jsp/%s.jsp",target);
    }
    private String getCommandPath(String target)  {
        return String.format("/controller?command=%s",target);
    }

    private FrontCommand getCommand(HttpServletRequest request){
        try {
            Class<?> type = Class.forName(String.format(
                    "com.epam.motrechko.commands.%sCommand",
                    request.getParameter("command")));
            return type
                    .asSubclass(FrontCommand.class)
                    .newInstance();
        } catch (Exception e){
            return new UnknownCommand();
        }
    }
}
