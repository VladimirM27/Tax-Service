package com.epam.motrechko.commands;

import jakarta.servlet.ServletException;

import java.io.IOException;

public class LogoutCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        request.getSession().removeAttribute("currentUser");
        response.sendRedirect(request.getContextPath() + "/jsp");
    }
}
