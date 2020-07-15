package mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.impl.AddBookCommand;
import com.mishamba.day6.controller.command.impl.RemoveBookCommand;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.entity.Library;
import com.mishamba.day6.model.exception.ModelException;
import mishamba.day6.controller.dataprovider.RemoveBookDataProvider;
import mishamba.day6.controller.dataprovider.StandardBooksStack;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.testng.Assert.*;

public class RemoveBookCommandTest {
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

    @Test(groups = "valid tests", dataProvider = "removebook valid command",
            dataProviderClass = RemoveBookDataProvider.class)
    public void testExecute_valid(String command, ArrayList<CustomBook> expected) {
        RemoveBookCommand removeBookCommand = new RemoveBookCommand();
        try {
            // TODO: 7/15/20 concurrentException
            ArrayList<CustomBook> actual = removeBookCommand.execute(command);
            assertEquals(actual, expected);
        } catch (ControllerException ex) {
            fail(ex.toString());
        }
    }

    @Test(groups = "invalid tests", dataProvider = "removebook invalid command",
            dataProviderClass = RemoveBookDataProvider.class,
            expectedExceptions = ControllerException.class)
    public void testExecute_invalid(String command) throws ControllerException {
        RemoveBookCommand removeBookCommand = new RemoveBookCommand();
        removeBookCommand.execute(command);
    }
}