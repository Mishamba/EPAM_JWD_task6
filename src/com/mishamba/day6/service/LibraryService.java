package com.mishamba.day6.service;

import com.mishamba.day6.model.entity.Book;
import com.mishamba.day6.model.exception.ModelException;
import com.mishamba.day6.service.exception.ServiceException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public interface LibraryService {
    void addBook(@NotNull String title, int pages,
                 ArrayList<String> authors) throws ModelException, ServiceException;
    void removeBook(@NotNull String title, int pages, ArrayList<String> authors)
            throws ServiceException;
    ArrayList<Book> findByTitle(String title) throws ModelException, ServiceException;
    ArrayList<Book> findByAuthors(String ... authors) throws ModelException, ServiceException;
    ArrayList<Book> findByPages(int pages) throws ModelException, ServiceException;
    ArrayList<Book> sortById() throws ServiceException;
    ArrayList<Book> sortByTitle() throws ServiceException;
    ArrayList<Book> sortByAuthors() throws ServiceException;
    ArrayList<Book> sortByPages() throws ServiceException;
}
