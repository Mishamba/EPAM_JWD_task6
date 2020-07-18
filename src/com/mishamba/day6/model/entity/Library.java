package com.mishamba.day6.model.entity;

import com.mishamba.day6.model.exception.ModelException;

import java.util.ArrayList;

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
        ArrayList<CustomBook> copiedBooks = new ArrayList<>();
        for (CustomBook book : this.books) {
            try {
                copiedBooks.add(CustomBook.Creator.create(
                        book.getTitle(),
                        book.getPages(),
                        book.getAuthors()));
            } catch (ModelException ignored) {
                // This data already validated.
            }
        }

        return copiedBooks;
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
