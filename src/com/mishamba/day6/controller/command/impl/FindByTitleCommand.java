package com.mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.Command;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.service.exception.ServiceException;
import com.mishamba.day6.service.impl.LibraryServiceImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FindByTitleCommand implements Command {
    private static final int COMMAND_LENGTH = 11;

    @Override
    public ArrayList<CustomBook> execute(String parameter) throws ControllerException {
        String title = formTitle(parameter);
        try {
            return LibraryServiceImpl.getInstance().findByTitle(title);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Contract(pure = true)
    private @NotNull String formTitle(@NotNull String parameter) {
        return parameter.substring(COMMAND_LENGTH + 1);
    }
}
