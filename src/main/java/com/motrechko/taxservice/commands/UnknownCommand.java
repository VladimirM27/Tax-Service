package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.enums.Target;
import com.motrechko.taxservice.utils.SessionUtils;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class UnknownCommand extends FrontCommand{
    @Override
    public CommandResponse process() throws ServletException, IOException {
        SessionUtils.setError(request,"Something went wrong");
        return new CommandResponse(Target.JSP,FrontConstant.ERROR);
    }
}
