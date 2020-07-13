package com.mishamba.day6.dao;

import com.mishamba.day6.dao.exception.DaoException;
import com.mishamba.day6.model.entity.Book;
import com.mishamba.day6.model.exception.ModelException;

import java.util.ArrayList;

public interface LibraryDataAccessObject {
    void addBook(Book book) throws ModelException, DaoException;
    void removeBook(Book book) throws ModelException, DaoException;
    ArrayList<Book> findByTitle(String title);
    ArrayList<Book> findByAuthors(String ... authors);
    ArrayList<Book> findByPages(int pages);
    ArrayList<Book> selectAllBooks();
}
