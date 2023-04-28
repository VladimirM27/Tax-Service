package com.motrechko.taxservice.utils;


import com.motrechko.taxservice.model.Inspector;
import com.motrechko.taxservice.model.User;
import jakarta.servlet.http.HttpServletRequest;



public class SessionUtils {
    private SessionUtils() {
        throw new AssertionError("This class should not be instantiated.");
    }

    public static void setError(HttpServletRequest request, String error){
        request.getSession().setAttribute("errorMessage", error);
    }

    public static User getSessionUser(HttpServletRequest request){
        return (User) request.getSession(false).getAttribute("currentUser");
    }
    public static Inspector getSessionInspector(HttpServletRequest request){
        return (Inspector) request.getSession(false).getAttribute("currentUser");
    }

}
