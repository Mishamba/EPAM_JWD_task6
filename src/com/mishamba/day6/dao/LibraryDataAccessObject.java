package com.mishamba.day6.dao;

import com.mishamba.day6.dao.exception.DaoException;
import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.exception.ModelException;

import java.util.ArrayList;

public interface LibraryDataAccessObject {
    void addBook(CustomBook book) throws ModelException, DaoException;
    void removeBook(CustomBook book) throws ModelException, DaoException;
    ArrayList<CustomBook> findByTitle(String title);
    ArrayList<CustomBook> findByAuthors(String ... authors);
    ArrayList<CustomBook> findByPages(int pages);
    ArrayList<CustomBook> selectAllBooks();
}
