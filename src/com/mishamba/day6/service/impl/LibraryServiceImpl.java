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
    public void removeBook(@NotNull String id, @NotNull String title,
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
    public Book findById(@NotNull String id) throws ModelException {
        LibraryDataAccessObjectImpl dataAccessObject = new LibraryDataAccessObjectImpl();
        Optional<Book> bookOptional = dataAccessObject.findById(id);
        if (bookOptional.isEmpty()) {
            throw new ModelException("no book with this id : " + id);
        }

        return bookOptional.get();
    }

    @Override
    public ArrayList<Book> findByTitle(@NotNull String title)
            throws ModelException {
        LibraryDataAccessObjectImpl dataAccessObject =
                new LibraryDataAccessObjectImpl();
        ArrayList<Book> books = dataAccessObject.findByTitle(title);
        if (books.isEmpty()) {
            throw new ModelException("no book with such title : " + title);
        }

        return books;
    }

    @Override
    public ArrayList<Book> findByAuthors(@NotNull String[] authors)
            throws ModelException {
        LibraryDataAccessObjectImpl dataAccessObject =
                new LibraryDataAccessObjectImpl();
        ArrayList<Book> books = dataAccessObject.findByAuthors(authors);
        if (books.isEmpty()) {
            throw new ModelException("no book with this authors : " +
                    Arrays.toString(authors));
        }

        return books;
    }

    @Override
    public ArrayList<Book> findByPages(int pages) throws ModelException {
        LibraryDataAccessObjectImpl dataAccessObject =
                new LibraryDataAccessObjectImpl();
        ArrayList<Book> books = dataAccessObject.findByPages(pages);
        if (books.isEmpty()) {
            throw new ModelException("no book with this count of pages : " +
                    pages);
        }

        return books;
    }

    @Override
    public ArrayList<Book> sortById() {
        Comparator id = com.mishamba.day6.model.comparator.Comparator::compareById;
        return sortBy(id);
    }

    @Override
    public ArrayList<Book> sortByTitle() {
        Comparator title = com.mishamba.day6.model.comparator.Comparator::compareByTitle;
        return sortBy(title);
    }

    @Override
    public ArrayList<Book> sortByAuthors() {
        Comparator authors = com.mishamba.day6.model.comparator.Comparator::compareByAuthors;
        return sortBy(authors);
    }

    @Override
    public ArrayList<Book> sortByPages() {
        Comparator pages = com.mishamba.day6.model.comparator.Comparator::compareByPages;
        return sortBy(pages);
    }

    private @NotNull ArrayList<Book> sortBy(Comparator comparator) {
        LibraryDataAccessObjectImpl dataAccessObject =
                new LibraryDataAccessObjectImpl();
        ArrayList<Book> books = dataAccessObject.selectAllBooks();
        books.sort(comparator);
        return books;
    }
}

interface Comparator extends java.util.Comparator<Book> {
    int compare(Book firstBook, Book secondBook);
}
