package com.epam.motrechko.commands;

import com.epam.motrechko.FrontConstant;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class UnknownCommand extends FrontCommand{
    @Override
    public String process() throws ServletException, IOException {
        return FrontConstant.ERROR;
    }
}
