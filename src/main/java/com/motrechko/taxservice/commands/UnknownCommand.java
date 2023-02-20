package com.motrechko.taxservice.commands;

import com.motrechko.taxservice.FrontConstant;
import com.motrechko.taxservice.enums.Target;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class UnknownCommand extends FrontCommand{
    @Override
    public CommandResponse process() throws ServletException, IOException {
        return new CommandResponse(Target.JSP,FrontConstant.ERROR);
    }
}
