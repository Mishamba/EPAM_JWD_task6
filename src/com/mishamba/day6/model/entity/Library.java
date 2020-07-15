package com.mishamba.day6.model.entity;

import com.mishamba.day6.model.exception.ModelException;

import java.util.ArrayList;
import java.util.Optional;

public class Library {
    private static Library instance;
    private static final int MAX_CAPACITY = 1000;
    private final ArrayList<CustomBook> books;

    private Library() {
        this.books = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }

        return instance;
    }

    public ArrayList<CustomBook> getBooks() {
        return new ArrayList<>(books);
    }

    public void addBook(CustomBook bookToAdd) throws ModelException {
        if (MAX_CAPACITY <= books.size()) {
            throw new ModelException("library is full");
        }

        for (CustomBook book : books) {
            if (book.equals(bookToAdd)) {
                throw new ModelException("this book already exist in library");
            }
        }

        bookToAdd.setId();
        books.add(bookToAdd);
    }

    public void removeBook(CustomBook bookToRemove) throws ModelException {
        int index = 0;
        for (CustomBook book : books) {
            if (book.equals(bookToRemove)) {
                books.remove(index);
            }

            index++;
        }

        throw new ModelException("no such book");
    }

    public Optional<CustomBook> findById(String id) {
        for (CustomBook book : books) {
            if (book.getId().equals(id)) {
                return Optional.of(book);
            }
        }

        return Optional.empty();
    }

    public ArrayList<CustomBook> findByTitle(String title) {
        ArrayList<CustomBook> searchResult = new ArrayList<>();
        for (CustomBook book : books) {
            if (book.getTitle().equals(title)) {
                searchResult.add(book);
            }
        }

        return searchResult;
    }

    public ArrayList<CustomBook> findByAuthors(String... authors) {
        ArrayList<CustomBook> searchResult = new ArrayList<>();
        boolean foundBook = false;
        for (CustomBook book : books) {
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

    public ArrayList<CustomBook> findByPages(int pages) {
        ArrayList<CustomBook> searchResult = new ArrayList<>();
        for (CustomBook book : books) {
            if (book.getPages() == pages) {
                searchResult.add(book);
            }
        }

        return searchResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Library)) return false;
        Library library = (Library) o;

        return this.books.equals(library.getBooks());
    }

    @Override
    public int hashCode() {
        int prime = 62;
        int hash = 0;
        for (CustomBook book : books) {
            hash += prime * book.hashCode();
        }

        return hash;
    }
}
