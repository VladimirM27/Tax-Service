package com.epam.motrechko.commands;

import com.epam.motrechko.FrontConstant;
import com.epam.motrechko.enums.Target;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class UnknownCommand extends FrontCommand{
    @Override
    public CommandResponse process() throws ServletException, IOException {
        return new CommandResponse(Target.JSP,FrontConstant.ERROR);
    }
}
