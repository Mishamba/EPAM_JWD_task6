package com.mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.Command;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.service.impl.LibraryServiceImpl;

import java.util.ArrayList;

public class WrongRequest implements Command {
    @Override
    public ArrayList<CustomBook> execute(String parameter) throws ControllerException {
        return LibraryServiceImpl.getInstance().selectAllBooks();
    }
}
