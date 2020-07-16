package mishamba.day6.controller.dataprovider;

import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.exception.ModelException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;

public class FindByTitleDataProvider {

    @Contract(" -> new")
    @DataProvider(name = "findbytitle valid commands")
    public static Object[] @NotNull [] findbytitleValidCommands()
            throws ModelException {
        return new Object[][] {
                {"findbytitle work", new ArrayList<>(Arrays.asList(
                        CustomBook.Creator.create("work", 60,
                                new ArrayList<>(Arrays.asList(
                                        "Show",
                                        "Bolton",
                                        "Kage")))))},
                {"findbytitle music", new ArrayList<>(Arrays.asList(
                        CustomBook.Creator.create("music", 400,
                                new ArrayList<>(Arrays.asList(
                                        "Hope",
                                        "Sakura")))))}
        };
    }

    @Contract(value = " -> new", pure = true)
    @DataProvider(name = "findbytitle invalid commands")
    public static Object[] @NotNull [] findbytitleInvalidCommands() {
        return new Object[][] {
                {"finbbytitle43d"},
                {"find 5"}
        };
    }
}
