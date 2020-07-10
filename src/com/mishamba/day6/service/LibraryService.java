package com.mishamba.day6.service;

import com.mishamba.day6.model.entity.Book;
import com.mishamba.day6.model.exception.ModelException;
import com.mishamba.day6.service.exception.ServiceException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public interface LibraryService {
    void addBook(@NotNull String title, int pages,
                 ArrayList<String> authors) throws ModelException, ServiceException;
    void removeBook(@NotNull String id, @NotNull String title, int pages,
                    ArrayList<String> authors) throws ModelException, ServiceException;
    Book findById(String id) throws ModelException;
    ArrayList<Book> findByTitle(String title) throws ModelException;
    ArrayList<Book> findByAuthors(String ... authors) throws ModelException;
    ArrayList<Book> findByPages(int pages) throws ModelException;
    ArrayList<Book> sortById();
    ArrayList<Book> sortByTitle();
    ArrayList<Book> sortByAuthors();
    ArrayList<Book> sortByPages();
}
