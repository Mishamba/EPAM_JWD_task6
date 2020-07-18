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
        ArrayList<CustomBook> searchResult = new ArrayList<>();
        for (CustomBook book : Library.getInstance().getBooks()) {
            if (book.getTitle().equals(title)) {
                searchResult.add(book);
            }
        }

        return searchResult;
    }

    @Override
    public ArrayList<CustomBook> findByAuthors(String... authors) {
        ArrayList<CustomBook> searchResult = new ArrayList<>();
        boolean foundBook = false;
        for (CustomBook book : Library.getInstance().getBooks()) {
            foundBook = false;
            ArrayList<String> bookAuthors = book.getAuthors();
            for (String bookAuthor : bookAuthors) {
                if (foundBook) {
                    break;
                }
                for (String givenAuthor : authors) {
                    if (bookAuthor.equals(givenAuthor)) {
                        searchResult.add(book);
                        foundBook = true;
                    }
                }
            }
        }

        return searchResult;
    }

    @Override
    public ArrayList<CustomBook> findByPages(int pages) {
        ArrayList<CustomBook> searchResult = new ArrayList<>();
        for (CustomBook book : Library.getInstance().getBooks()) {
            if (book.getPages() == pages) {
                searchResult.add(book);
            }
        }

        return searchResult;
    }

    @Override
    public ArrayList<CustomBook> selectAllBooks() {
        return Library.getInstance().getBooks();
    }
}
