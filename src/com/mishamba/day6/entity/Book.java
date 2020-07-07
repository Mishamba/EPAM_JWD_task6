package com.mishamba.day6.entity;

import com.mishamba.day6.exception.ProgramException;
import com.mishamba.day6.validator.NegativePagesValidator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Book {
    private UUID id;
    private final String name;
    private final ArrayList<String> authors;
    private final int pages;

    public Book(String name, int pages, String ... authors) {
        this.name = name;
        this.pages = pages;
        this.authors = new ArrayList<>(Arrays.asList(authors));
    }

    public String getId() {
        return id.toString();
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public int getPages() {
        return pages;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    static class Creator {
        @Contract("_, _, _ -> new")
        public static @NotNull Book create(@NotNull String name, int pages,
                                           @NotNull String ... authors) throws ProgramException {
            NegativePagesValidator validator = new NegativePagesValidator();
            if (validator.notNegative(pages)) {
                throw new ProgramException("negative pages");
            }

            return new Book(name, pages, authors);
        }
    }

    static class Comparator {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }

        Book book = (Book) o;

        return book.getName().equals(this.getName()) &&
                book.getPages() == this.getPages() &&
                book.getAuthors().equals(this.getAuthors());
    }

    @Override
    public int hashCode() { // TODO: 7/7/20 make cooler hash
        return 53 * (pages + authors.size());
    }
}
