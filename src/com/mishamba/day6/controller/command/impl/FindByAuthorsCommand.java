package com.mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.Command;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.service.exception.ServiceException;
import com.mishamba.day6.service.impl.LibraryServiceImpl;
import org.jetbrains.annotations.NotNull;

public class FindByAuthorsCommand implements Command {
    private static final int COMMAND_LENGTH = 13;
    @Override
    public String execute(@NotNull String parameter) throws ControllerException {
        String[] authors = formAuthors(parameter);
        try {
            return LibraryServiceImpl.getInstance().
                    findByAuthors(authors).toString();
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    private String[] formAuthors(@NotNull String parameters) {
        return parameters.trim().substring(COMMAND_LENGTH + 1, parameters.length()).
                split(" ");
    }
}
