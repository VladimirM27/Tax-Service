package com.epam.motrechko.commands;

import jakarta.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class RegistrationCommand extends FrontCommand{

    private final static Logger logger = LogManager.getLogger(RegistrationCommand.class);
    @Override
    public void process() throws ServletException, IOException {
        String email = request.getParameter("emailAddress");
        String password = request.getParameter("secondPassword");
        String entity = request.getParameter("entity");
        String role = "user";
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
    }
}
