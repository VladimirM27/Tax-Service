package com.epam.motrechko.commands;

import com.epam.motrechko.FrontConstant;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class LogoutCommand extends FrontCommand{
    @Override
    public String process() throws ServletException, IOException {
        request.getSession().removeAttribute("currentUser");

        return FrontConstant.INDEX;
    }
}
