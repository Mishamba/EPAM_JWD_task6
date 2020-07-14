package com.mishamba.day6.controller;

import com.mishamba.day6.controller.command.Command;
import com.mishamba.day6.controller.command.factory.CommandProvider;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.service.impl.LibraryServiceImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Invoker {
    private static final Logger logger = Logger.getRootLogger();
    private static Invoker instance;

    private Invoker() {
        String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
    }

    public static Invoker getInstance() {
        if (instance == null) {
            instance = new Invoker();
        }

        return instance;
    }

    public ArrayList<CustomBook> executeCommand(@NotNull String command) {
        String commandName = findCommandName(command);
        Command executionCommand = CommandProvider.getInstance().
                getCommand(commandName);

        ArrayList<CustomBook> books;

        try {
             books = executionCommand.execute(command);
        } catch (ControllerException ex) {
            logger.warn("couldn't execute command: " + commandName +
                    " with call: " + command);
            books = LibraryServiceImpl.getInstance().selectAllBooks();
        }

        return books;
    }

    private @NotNull String findCommandName(@NotNull String command) {
        String delimiter = "\\s";
        return command.substring(0, command.indexOf(delimiter));
    }
}
