package mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.impl.FindByAuthorsCommand;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.entity.Library;
import com.mishamba.day6.model.exception.ModelException;
import mishamba.day6.controller.dataprovider.FindByAuthorsDataProvider;
import mishamba.day6.controller.dataprovider.StandardBooksStack;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class FindByAuthorsCommandTest {

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

    @Test(dataProvider = "findbyauthors valid commands",
            dataProviderClass = FindByAuthorsDataProvider.class)
    public void testExecute_valid(String command,
                                  ArrayList<CustomBook> expected) {
        FindByAuthorsCommand findByAuthorsCommand = new FindByAuthorsCommand();
        try {
            ArrayList<CustomBook> actual = findByAuthorsCommand.execute(command);
            assertEquals(actual, expected);
        } catch (ControllerException ex) {
            fail(ex.toString());
        }
    }

    @Test(dataProvider = "findbyauthors invalid commands",
            dataProviderClass = FindByAuthorsDataProvider.class,
            expectedExceptions = ControllerException.class)
    public void testExecute_invalid(String command) throws ControllerException {
        FindByAuthorsCommand findByAuthorsCommand = new FindByAuthorsCommand();
        findByAuthorsCommand.execute(command);
    }
}