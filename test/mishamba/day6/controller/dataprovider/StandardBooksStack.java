package mishamba.day6.controller.dataprovider;

import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.exception.ModelException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class StandardBooksStack {
    public static @NotNull ArrayList<CustomBook> get() {
        ArrayList<CustomBook> books = new ArrayList<>();
        try {
            books.add(CustomBook.Creator.create("work", 60,
                    new ArrayList<>(Arrays.asList("Show", "Bolton", "Kage"))));
            books.add(CustomBook.Creator.create("field", 500,
                    new ArrayList<>(Arrays.asList("Police", "Forty", "PUP"))));
            books.add(CustomBook.Creator.create("music", 400,
                    new ArrayList<>(Arrays.asList("Hope", "Sakura"))));
            books.add(CustomBook.Creator.create("tests", 300,
                    new ArrayList<>(Arrays.asList("Devil", "god"))));
        } catch (ModelException ignored) {
        }

        return books;
    }
}
