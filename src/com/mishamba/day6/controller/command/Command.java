package com.mishamba.day6.controller.command;

import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.model.entity.CustomBook;

import java.util.ArrayList;

public interface Command {
    ArrayList<CustomBook> execute(String parameter) throws ControllerException;
}
