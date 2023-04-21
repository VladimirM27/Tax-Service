package com.motrechko.taxservice.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class NotFoundRedirectFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;



        if (response.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
            response.sendRedirect(request.getContextPath() + "/jsp/error404.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
