package com.mishamba.day6.controller;

import com.mishamba.day6.controller.command.Command;
import com.mishamba.day6.controller.command.factory.CommandProvider;
import com.mishamba.day6.controller.exception.ControllerException;
import org.jetbrains.annotations.NotNull;

public class Invoker {
    private final String BAD_RESPONSE = "smth went wrong. now check this out";

    public String executeCommand(@NotNull String command) {
        String commandName;
        Command executionCommand;

        char delimeter = ' ';
        commandName = command.substring(0, command.indexOf(delimeter));
        executionCommand = CommandProvider.getInstance().
                getCommand(commandName);

        String responce;

        try {
            responce = executionCommand.execute(command);
        } catch (ControllerException ex) {
            responce = BAD_RESPONSE.concat(" " + ex.toString());
        }

        return responce;
    }
}
