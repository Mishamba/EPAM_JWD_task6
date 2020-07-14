package com.mishamba.day6.dao.impl;

import com.mishamba.day6.dao.LibraryDataAccessObject;
import com.mishamba.day6.dao.exception.DaoException;
import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.entity.Library;
import com.mishamba.day6.model.exception.ModelException;

import java.util.ArrayList;

public class LibraryDataAccessObjectImpl implements LibraryDataAccessObject {
    @Override
    public void addBook(CustomBook book) throws DaoException {
        try {
            Library.getInstance().addBook(book);
        } catch (ModelException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void removeBook(CustomBook book) throws DaoException {
        try {
            Library.getInstance().removeBook(book);
        } catch (ModelException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public ArrayList<CustomBook> findByTitle(String title) {
        return Library.getInstance().findByTitle(title);
    }

    @Override
    public ArrayList<CustomBook> findByAuthors(String... authors) {
        return Library.getInstance().findByAuthors();
    }

    @Override
    public ArrayList<CustomBook> findByPages(int pages) {
        return Library.getInstance().findByPages(pages);
    }

    @Override
    public ArrayList<CustomBook> selectAllBooks() {
        return Library.getInstance().getBooks();
    }
}
