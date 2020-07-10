package com.mishamba.day6.model.comparator;

import com.mishamba.day6.model.entity.Book;
import org.jetbrains.annotations.NotNull;

public class Comparator {
    private Comparator() {

    }

    public static int compareById(@NotNull Book firstBook, @NotNull Book secondBook) {
        return firstBook.getId().compareTo(secondBook.getId());
    }

    public static int compareByTitle(@NotNull Book firstBook, @NotNull Book secondBook) {
        return firstBook.getTitle().compareTo(secondBook.getTitle());
    }

    public static int compareByAuthors(@NotNull Book firstBook, @NotNull Book secondBook) {
        if (firstBook.getAuthors().size() > secondBook.getAuthors().size()) {
            return 1;
        } else if (firstBook.getAuthors().size() < secondBook.getAuthors().size()) {
            return -1;
        }

        int resultCompare = 0;
        for (int i = 0; i < firstBook.getAuthors().size(); i++) {
            resultCompare += firstBook.getAuthors().get(i).
                    compareTo(secondBook.getAuthors().get(i));
        }

        if (resultCompare > 1) {
            resultCompare = 1;
        } else if (resultCompare < -1) {
            resultCompare = -1;
        }

        return resultCompare;
    }

    public static int compareByPages(@NotNull Book firstBook, @NotNull Book secondBook) {
        int resultCompare = (firstBook.getPages() >= secondBook.getPages()) ? 1 : -1;
        if (firstBook.getPages() == secondBook.getPages()) {
            resultCompare = 0;
        }

        return resultCompare;
    }
}
