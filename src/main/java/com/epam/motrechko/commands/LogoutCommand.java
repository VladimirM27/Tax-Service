package com.epam.motrechko.commands;

import com.epam.motrechko.FrontConstant;
import com.epam.motrechko.enums.Target;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class LogoutCommand extends FrontCommand{
    @Override
    public CommandResponse process() throws ServletException, IOException {
        request.getSession().removeAttribute("currentUser");
        return new CommandResponse(Target.JSP,FrontConstant.INDEX);

    }
}
