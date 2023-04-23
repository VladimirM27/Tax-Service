package com.motrechko.taxservice.utils;


import jakarta.servlet.http.HttpServletRequest;



public class SessionUtils {
    private SessionUtils() {
        throw new AssertionError("This class should not be instantiated.");
    }

    public static void setError(HttpServletRequest request, String error){
        request.getSession().setAttribute("errorMessage", error);
    }

}
