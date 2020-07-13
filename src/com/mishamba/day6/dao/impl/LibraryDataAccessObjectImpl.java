package com.mishamba.day6.dao.impl;

import com.mishamba.day6.dao.LibraryDataAccessObject;
import com.mishamba.day6.dao.exception.DaoException;
import com.mishamba.day6.model.entity.Book;
import com.mishamba.day6.model.entity.Library;
import com.mishamba.day6.model.exception.ModelException;

import java.util.ArrayList;

public class LibraryDataAccessObjectImpl implements LibraryDataAccessObject {
    @Override
    public void addBook(Book book) throws DaoException {
        try {
            Library.getInstance().addBook(book);
        } catch (ModelException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void removeBook(Book book) throws DaoException {
        try {
            Library.getInstance().removeBook(book);
        } catch (ModelException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public ArrayList<Book> findByTitle(String title) {
        return Library.getInstance().findByTitle(title);
    }

    @Override
    public ArrayList<Book> findByAuthors(String... authors) {
        return Library.getInstance().findByAuthors();
    }

    @Override
    public ArrayList<Book> findByPages(int pages) {
        return Library.getInstance().findByPages(pages);
    }

    @Override
    public ArrayList<Book> selectAllBooks() {
        return Library.getInstance().getBooks();
    }
}
