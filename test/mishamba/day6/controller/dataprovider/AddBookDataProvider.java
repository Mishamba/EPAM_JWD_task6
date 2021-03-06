package mishamba.day6.controller.dataprovider;

import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.exception.ModelException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;

public class AddBookDataProvider {

    @Contract(value = " -> new", pure = true)
    @DataProvider(name = "addbook invalid command")
    public static Object[] @NotNull [] addbookInvalidCommands() {
        return new Object[][] {
                {"addbookjava8700mishambagleckkek"},
                {"wtf"},
                {"i've tired to write this"},
                {"addbook java3 mishamba gleck"}
        };
    }

    @Contract(" -> new")
    @DataProvider(name = "addbook valid command")
    public static Object[] @NotNull [] addbookValidCommands()
            throws ModelException {
        ArrayList<CustomBook> books = StandardBooksStack.get();
        books.add(CustomBook.Creator.create("java8", 700,
                new ArrayList<>(Arrays.asList("mishamba", "gleck", "kek"))));
        return new Object[][]{
                {"addbook java8 700 mishamba gleck kek", books}
        };
    }
}
