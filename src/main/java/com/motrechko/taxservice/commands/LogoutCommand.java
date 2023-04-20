package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.enums.Target;
import jakarta.servlet.ServletException;

import java.io.IOException;


public class LogoutCommand extends FrontCommand{
    /**
        Removes the "currentUser" attribute from the session and redirects the user to the index page.
        @return a CommandResponse with the index page as target.
        @throws ServletException if an error occurs during the servlet execution.
        @throws IOException if an error occurs during the input/output operations.
    */
    @Override
    public CommandResponse process() throws ServletException, IOException {
        request.getSession().removeAttribute("currentUser");
        return new CommandResponse(Target.JSP,FrontConstant.INDEX);
    }
}
