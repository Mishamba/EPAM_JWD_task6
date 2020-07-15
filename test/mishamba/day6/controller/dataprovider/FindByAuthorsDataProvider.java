package mishamba.day6.controller.dataprovider;

import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.exception.ModelException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class FindByAuthorsDataProvider {

    @Contract(value = " -> new", pure = true)
    @DataProvider(name = "findbyauthors invalid commands")
    public static Object[] @NotNull [] findbyauthorsInvalidCommands() {
        return new Object[][] {
                {"findbkshtnbofankjartduovnaskjl"},
                {"findbyauthor d b s"},
                {"addbook dtd"}
        };
    }

    @Contract(" -> new")
    @DataProvider(name = "findbyauthors valid commands")
    public static Object[] @NotNull [] findbyauthorsValidCommands()
            throws ModelException {
        return new Object[][] {
                {"findbyauthors Show Bolton Kage",
                        new ArrayList<>(Arrays.asList(CustomBook.Creator.create(
                                "work",
                                60,
                                new ArrayList<>(Arrays.asList(
                                        "Show",
                                        "Bolton",
                                        "Kage")))))},
                {"findbyauthors Police",
                        new ArrayList<>(Arrays.asList(CustomBook.Creator.create(
                                "field",
                                500,
                                new ArrayList<>(Arrays.asList(
                                        "Police",
                                        "Forty",
                                        "PUP")))))}
        };
    }
}
