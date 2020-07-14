package com.mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.Command;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.service.exception.ServiceException;
import com.mishamba.day6.service.impl.LibraryServiceImpl;

import java.util.ArrayList;

public class SortByTitleCommand implements Command {
    @Override
    public ArrayList<CustomBook> execute(String parameter) throws ControllerException {
        try {
            return LibraryServiceImpl.getInstance().sortByTitle();
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
    }
}
