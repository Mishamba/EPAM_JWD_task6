package com.mishamba.day6.service.impl;

import com.mishamba.day6.dao.exception.DaoException;
import com.mishamba.day6.dao.impl.LibraryDataAccessObjectImpl;
import com.mishamba.day6.model.entity.Book;
import com.mishamba.day6.model.exception.ModelException;
import com.mishamba.day6.service.LibraryService;
import com.mishamba.day6.service.exception.ServiceException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class LibraryServiceImpl implements LibraryService {
    private static LibraryServiceImpl instance;

    private LibraryServiceImpl() {

    }

    public static LibraryServiceImpl getInstance() {
        if (instance == null) {
            instance = new LibraryServiceImpl();
        }

        return instance;
    }

    @Override
    public void addBook(@NotNull String title, int pages,
                        ArrayList<String> authors) throws ServiceException {
        LibraryDataAccessObjectImpl dataAccessObject =
                new LibraryDataAccessObjectImpl();
        try {
            Book book = Book.Creator.create(title, pages, authors);
            dataAccessObject.addBook(book);
        } catch (ModelException | DaoException ex) {
            throw new ServiceException("can't add book", ex);
        }
    }

    @Override
    public void removeBook(@NotNull String title,
                           int pages, ArrayList<String> authors)
            throws ServiceException {
        LibraryDataAccessObjectImpl dataAccessObject =
                new LibraryDataAccessObjectImpl();
        try {
            Book book = Book.Creator.create(title, pages, authors);
            dataAccessObject.removeBook(book);
        } catch (DaoException | ModelException ex) {
            throw new ServiceException("can't remove book", ex);
        }
    }

    @Override
    public ArrayList<Book> findByTitle(@NotNull String title)
            throws ServiceException {
        LibraryDataAccessObjectImpl dataAccessObject =
                new LibraryDataAccessObjectImpl();
        ArrayList<Book> books = dataAccessObject.findByTitle(title);
        if (books.isEmpty()) {
            throw new ServiceException("no book with such title : " + title);
        }

        return books;
    }

    @Override
    public ArrayList<Book> findByAuthors(@NotNull String[] authors)
            throws ServiceException {
        LibraryDataAccessObjectImpl dataAccessObject =
                new LibraryDataAccessObjectImpl();
        ArrayList<Book> books = dataAccessObject.findByAuthors(authors);
        if (books.isEmpty()) {
            throw new ServiceException("no book with this authors : " +
                    Arrays.toString(authors));
        }

        return books;
    }

    @Override
    public ArrayList<Book> findByPages(int pages) throws ServiceException {
        LibraryDataAccessObjectImpl dataAccessObject =
                new LibraryDataAccessObjectImpl();
        ArrayList<Book> books = dataAccessObject.findByPages(pages);
        if (books.isEmpty()) {
            throw new ServiceException("no book with this count of pages : " +
                    pages);
        }

        return books;
    }

    @Override
    public ArrayList<Book> sortById() throws ServiceException {
        Comparator id = com.mishamba.day6.model.comparator.
                Comparator::compareById;
        return sortBy(id);
    }

    @Override
    public ArrayList<Book> sortByTitle() throws ServiceException {
        Comparator title = com.mishamba.day6.model.comparator.
                Comparator::compareByTitle;
        return sortBy(title);
    }

    @Override
    public ArrayList<Book> sortByAuthors() throws ServiceException {
        Comparator authors = com.mishamba.day6.model.comparator.
                Comparator::compareByAuthors;
        return sortBy(authors);
    }

    @Override
    public ArrayList<Book> sortByPages() throws ServiceException {
        Comparator pages = com.mishamba.day6.model.comparator.
                Comparator::compareByPages;
        return sortBy(pages);
    }

    private @NotNull ArrayList<Book> sortBy(Comparator comparator) throws ServiceException {
        LibraryDataAccessObjectImpl dataAccessObject =
                new LibraryDataAccessObjectImpl();
        ArrayList<Book> books = dataAccessObject.selectAllBooks();
        if (books.isEmpty()) {
            throw new ServiceException("no books found");
        }
        books.sort(comparator);
        return books;
    }
}

interface Comparator extends java.util.Comparator<Book> {
    int compare(Book firstBook, Book secondBook);
}
