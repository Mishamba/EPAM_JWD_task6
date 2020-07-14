package com.mishamba.day6.model.entity;

import com.mishamba.day6.model.exception.ModelException;
import com.mishamba.day6.validator.NegativePagesValidator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CustomBook {
    private UUID id;
    private final String title;
    private final ArrayList<String> authors;
    private final int pages;

    public CustomBook(String title, int pages, ArrayList<String> authors) {
        this.title = title;
        this.pages = pages;
        this.authors = authors;
    }

    public String getId() {
        return id.toString();
    }

    public String getTitle() {
        return title;
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

    public static class Creator {
        @Contract("_, _, _ -> new")
        public static @NotNull CustomBook create(@NotNull String title, int pages,
                                                 @NotNull ArrayList<String> authors) throws ModelException {
            NegativePagesValidator validator = new NegativePagesValidator();
            if (!validator.isNegative(pages)) {
                throw new ModelException("negative pages");
            }

            return new CustomBook(title, pages, authors);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomBook)) {
            return false;
        }

        CustomBook book = (CustomBook) o;

        return book.getTitle().equals(this.getTitle()) &&
                book.getPages() == this.getPages() &&
                book.getAuthors().equals(this.getAuthors());
    }

    @Override
    public int hashCode() {
        int prime = 72;
        int hashCode = prime * pages;
        hashCode += prime * title.hashCode();
        for (String author : this.getAuthors()) {
            hashCode += author.hashCode();
        }

        return hashCode;
    }
}
