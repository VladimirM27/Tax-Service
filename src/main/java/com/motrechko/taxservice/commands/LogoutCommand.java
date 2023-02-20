package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.enums.Target;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class LogoutCommand extends FrontCommand{
    @Override
    public CommandResponse process() throws ServletException, IOException {
        request.getSession().removeAttribute("currentUser");
        return new CommandResponse(Target.JSP,FrontConstant.INDEX);

    }
}
