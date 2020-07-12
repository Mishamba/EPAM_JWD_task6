package com.mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.Command;
import com.mishamba.day6.controller.exception.ControllerException;

public class WrongRequest implements Command {
    private final String ANSWER = "command incorrect";

    @Override
    public String execute(String parameter) throws ControllerException {
        return ANSWER;
    }
}
