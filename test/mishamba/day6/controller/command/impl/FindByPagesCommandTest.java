package mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.impl.FindByPagesCommand;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.entity.Library;
import com.mishamba.day6.model.exception.ModelException;
import mishamba.day6.controller.dataprovider.FindByPagesDataProvider;
import mishamba.day6.controller.dataprovider.StandardBooksStack;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class FindByPagesCommandTest {

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

    @Test(dataProviderClass = FindByPagesDataProvider.class,
            dataProvider = "findbypages valid commands")
    public void testExecute_valid(String command,
                                  ArrayList<CustomBook> expected) {
        FindByPagesCommand findByPagesCommand = new FindByPagesCommand();
        try {
            ArrayList<CustomBook> actual = findByPagesCommand.execute(command);
            assertEquals(actual, expected);
        } catch (ControllerException ex) {
            fail(ex.toString());
        }
    }

    @Test(dataProviderClass = FindByPagesDataProvider.class,
            dataProvider = "findbypages invalid commands",
            expectedExceptions = ControllerException.class)
    public void testExecute_invalid(String command) throws ControllerException {
        FindByPagesCommand findByPagesCommand = new FindByPagesCommand();
        findByPagesCommand.execute(command);
    }
}