package com.mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.Command;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.service.exception.ServiceException;
import com.mishamba.day6.service.impl.LibraryServiceImpl;

public class SortByPagesCommand implements Command {
    @Override
    public String execute(String parameter) throws ControllerException {
        try {
            return LibraryServiceImpl.getInstance().sortByPages().toString();
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
    }
}
