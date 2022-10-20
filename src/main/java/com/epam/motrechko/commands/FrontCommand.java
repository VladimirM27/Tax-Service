package com.epam.motrechko.commands;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class FrontCommand {
    protected ServletContext servletContext;
    protected HttpServletRequest  request;
    protected HttpServletResponse  response;

    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        this.servletContext = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
    }

    public abstract void process() throws ServletException, IOException;

    protected void forward(String target) throws ServletException, IOException {
        target = String.format("/WEB-INF/jsp/$s.jsp",target);
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher(target);
        dispatcher.forward(request,response);
    }
}
