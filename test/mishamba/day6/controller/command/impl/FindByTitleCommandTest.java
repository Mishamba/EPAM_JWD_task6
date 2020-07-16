package mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.impl.FindByTitleCommand;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.entity.Library;
import com.mishamba.day6.model.exception.ModelException;
import mishamba.day6.controller.dataprovider.FindByTitleDataProvider;
import mishamba.day6.controller.dataprovider.StandardBooksStack;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class FindByTitleCommandTest {

    @BeforeTest
    public void resetLibrary() {
        ArrayList<CustomBook> books = Library.getInstance().getBooks();
        for (CustomBook book : books) {
            try {
                Library.getInstance().removeBook(book);
            } catch (ModelException ignored) {
            }
        }

        books = StandardBooksStack.get();
        for (CustomBook book : books) {
            try {
                Library.getInstance().addBook(book);
            } catch (ModelException ignored) {
            }
        }
    }

    @Test(dataProvider = "findbytitle valid commands",
            dataProviderClass = FindByTitleDataProvider.class)
    public void testExecute_valid(String command,
                                  ArrayList<CustomBook> expected) {
        FindByTitleCommand findByTitleCommand = new FindByTitleCommand();
        try {
            ArrayList<CustomBook> actual = findByTitleCommand.execute(command);
            assertEquals(actual, expected);
        } catch (ControllerException ex) {
            fail(ex.toString());
        }
    }

    @Test(dataProvider = "findbytitle invalid commands",
            dataProviderClass = FindByTitleDataProvider.class,
            expectedExceptions = ControllerException.class)
    public void testExecute_invalid(String command) throws ControllerException {
        FindByTitleCommand findByTitleCommand = new FindByTitleCommand();
        findByTitleCommand.execute(command);
    }
}