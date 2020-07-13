package com.mishamba.day6.model.entity;

import com.mishamba.day6.model.exception.ModelException;

import java.util.ArrayList;
import java.util.Optional;

public class Library {
    private static Library instance;
    private static final int MAX_CAPACITY = 1000;
    private final ArrayList<Book> books;

    private Library() {
        this.books = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }

        return instance;
    }

    public ArrayList<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public void addBook(Book bookToAdd) throws ModelException {
        if (MAX_CAPACITY <= books.size()) {
            throw new ModelException("library is full");
        }

        for (Book book : books) {
            if (book.equals(bookToAdd)) {
                throw new ModelException("this book already exist in library");
            }
        }

        bookToAdd.setId();
        books.add(bookToAdd);
    }

    public void removeBook(Book bookToRemove) throws ModelException {
        int index = 0;
        for (Book book : books) {
            if (book.equals(bookToRemove)) {
                books.remove(index);
            }

            index++;
        }

        throw new ModelException("no such book");
    }

    public Optional<Book> findById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return Optional.of(book);
            }
        }

        return Optional.empty();
    }

    public ArrayList<Book> findByTitle(String title) {
        ArrayList<Book> searchResult = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                searchResult.add(book);
            }
        }

        return searchResult;
    }

    public ArrayList<Book> findByAuthors(String ... authors) {
        ArrayList<Book> searchResult = new ArrayList<>();
        for (Book book : books) {
            for (int i = 0; i< authors.length;i++) {
                for (String author : authors) {
                    if (book.getAuthors().get(i).equals(author)) {
                        searchResult.add(book);
                    }
                }
            }
        }

        return searchResult;
    }

    public ArrayList<Book> findByPages(int pages) {
        ArrayList<Book> searchResult = new ArrayList<>();
        for (Book book : books) {
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
        for (Book book : books) {
            hash += prime * book.hashCode();
        }

        return hash;
    }
}
