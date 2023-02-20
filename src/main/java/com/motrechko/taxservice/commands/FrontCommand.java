package com.motrechko.taxservice.commands;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


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

    public abstract CommandResponse process() throws ServletException, IOException;




}
