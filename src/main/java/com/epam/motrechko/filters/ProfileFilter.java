package com.epam.motrechko.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


@WebFilter( urlPatterns = {"/jsp/profile.jsp","/jsp/newReport.jsp","/jsp/reports.jsp","/jsp/raxForm.jsp"})
public class ProfileFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(ProfileFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("currentUser") != null;

        if(loggedIn){
            filterChain.doFilter(request,response);
        } else {
            logger.debug("User must auth before using profile page");
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
        }

    }
}
