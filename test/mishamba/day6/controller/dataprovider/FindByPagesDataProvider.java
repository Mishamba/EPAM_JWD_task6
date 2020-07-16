package mishamba.day6.controller.dataprovider;

import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.exception.ModelException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;

public class FindByPagesDataProvider {

    @Contract(" -> new")
    @DataProvider(name = "findbypages valid commands")
    public static Object[] @NotNull [] findbypagesValidCommands() throws ModelException {
        return new Object[][] {
                {"findbypages 60", new ArrayList<>(Arrays.asList(
                        CustomBook.Creator.create("work", 60,
                                new ArrayList<>(Arrays.asList(
                                        "Show",
                                        "Bolton",
                                        "Kage")))))},
                {"findbypages 300", new ArrayList<>(Arrays.asList(
                        CustomBook.Creator.create("tests", 300,
                                new ArrayList<>(Arrays.asList("Devil", "god")))))}
        };
    }

    @Contract(value = " -> new", pure = true)
    @DataProvider(name = "findbypages invalid commands")
    public static Object[] @NotNull [] findbypagesInvalidCommands() {
        return new Object[][] {
                {"findbypages60"},
                {"a;ngu sngfd"},
                {"findbypages find"}
        };
    }
}
