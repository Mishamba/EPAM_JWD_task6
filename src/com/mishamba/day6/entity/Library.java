package com.mishamba.day6.entity;

import com.mishamba.day6.exception.ProgramException;

import java.util.ArrayList;

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

    public static int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book bookToAdd) throws ProgramException {
        if (MAX_CAPACITY >= books.size()) {
            throw new ProgramException("library is full");
        }

        for (Book book : books) {
            if (book.equals(bookToAdd)) {
                throw new ProgramException("this book already exist in library");
            }
        }

        bookToAdd.setId();
        books.add(bookToAdd);
    }

    public void removeBook(Book bookToRemove) throws ProgramException {
        int index = 0;
        for (Book book : books) {
            if (book.equals(bookToRemove)) {
                books.remove(index);
            }

            index++;
        }

        throw new ProgramException("no such book");
    }
}
