package com.mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.Command;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.service.exception.ServiceException;
import com.mishamba.day6.service.impl.LibraryServiceImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveBookCommand implements Command {
    private static final int COMMAND_LENGTH = 10;
    private static final String SUCCESS_RESULT_MESSAGE = "book removed successfully";
    private static final String PAGES_REGEX = "(?<=\\w{7}\\s\\w\\+\\s)\\d+";
    private static final String BOOK_NAME_REGEX = "(?<=\\w{7}\\s)\\w+";

    public String execute(String parameter) throws ControllerException {
        String bookName = formBookName(parameter);
        int pages = formPages(parameter);
        ArrayList<String> authors = formAuthors(parameter);
        try {
            LibraryServiceImpl.getInstance().removeBook(bookName,
                    pages, authors);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }

        return SUCCESS_RESULT_MESSAGE;
    }

    private String formBookName(String parameters) {
        Pattern pattern = Pattern.compile(BOOK_NAME_REGEX);
        Matcher matcher = pattern.matcher(parameters);
        return matcher.group();
    }

    private int formPages(String parameters) {
        Pattern pattern = Pattern.compile(PAGES_REGEX);
        Matcher matcher = pattern.matcher(parameters);
        return Integer.parseInt(matcher.group());
    }

    @Contract("_ -> new")
    private @NotNull ArrayList<String> formAuthors(@NotNull String parameters) {
        String[] authors = parameters.
                substring(COMMAND_LENGTH + 1).
                split(" ");
        return new ArrayList<>(Arrays.asList(authors).
                subList(3, authors.length));
    }
}

