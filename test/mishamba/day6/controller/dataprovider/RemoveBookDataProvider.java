package mishamba.day6.controller.dataprovider;

import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.exception.ModelException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;

public class RemoveBookDataProvider {

    @Contract(value = " -> new", pure = true)
    @DataProvider(name = "removebook invalid command")
    public static Object[] @NotNull [] removebookInvalidCommands() {
        return new Object[][] {
                {"removebookjava8700mishambagleckkek"},
                {"wtf"},
                {"i've tired to write this"},
                {"removebook java3 mishamba gleck"}
        };
    }

    @Contract(" -> new")
    @DataProvider(name = "removebook valid command")
    public static Object[] @NotNull [] addbookInvalidCommands()
            throws ModelException {
        ArrayList<CustomBook> books = StandardBooksStack.get();
        books.remove(CustomBook.Creator.create("work", 60,
                new ArrayList<>(Arrays.asList("Show", "Bolton", "Kage"))));
        return new Object[][]{
                {"removebook work 60 Show Bolton Kage", books}
        };
    }
}
