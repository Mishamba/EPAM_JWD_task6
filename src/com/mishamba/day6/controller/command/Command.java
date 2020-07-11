package com.mishamba.day6.controller.command;

import com.mishamba.day6.controller.exception.ControllerException;

public interface Command {
    String execute(String parameter) throws ControllerException;
}
