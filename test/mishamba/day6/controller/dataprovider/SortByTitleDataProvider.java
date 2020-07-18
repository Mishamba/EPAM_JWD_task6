package mishamba.day6.controller.dataprovider;

import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.exception.ModelException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;

public class SortByTitleDataProvider {
    @Contract(" -> new")
    @DataProvider(name = "sortbytitle sorted order")
    public static Object[] @NotNull [] sortedByTitle() throws ModelException {
        return new Object[][]{
                {new ArrayList<CustomBook>(Arrays.asList(
                        CustomBook.Creator.create("field", 500,
                                new ArrayList<>(Arrays.asList(
                                        "Police",
                                        "Forty",
                                        "PUP"))),
                        CustomBook.Creator.create("music", 400,
                                new ArrayList<>(Arrays.asList(
                                        "Hope",
                                        "Sakura"))),
                        CustomBook.Creator.create("tests", 300,
                                new ArrayList<>(Arrays.asList(
                                        "Devil",
                                        "god"))),
                        CustomBook.Creator.create("work", 60,
                                new ArrayList<>(Arrays.asList(
                                        "Show",
                                        "Bolton",
                                        "Kage")))))}
        };
    }
}
