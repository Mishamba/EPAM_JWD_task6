package com.mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.Command;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.service.exception.ServiceException;
import com.mishamba.day6.service.impl.LibraryServiceImpl;
import org.jetbrains.annotations.NotNull;

public class FindByPagesCommand implements Command {
    private static final int COMMAND_LENGTH = 11;
    @Override
    public String execute(@NotNull String parameter) throws ControllerException {
        int pages = formPages(parameter);
        try {
            return LibraryServiceImpl.getInstance().
                    findByPages(pages).toString();
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    private int formPages(@NotNull String parameters) {
        return Integer.parseInt(parameters.trim().
                substring(COMMAND_LENGTH + 1, parameters.length()));
    }
}
