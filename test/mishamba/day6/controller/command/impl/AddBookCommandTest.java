package mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.impl.AddBookCommand;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.entity.Library;
import com.mishamba.day6.model.exception.ModelException;
import mishamba.day6.controller.dataprovider.AddBookDataProvider;
import mishamba.day6.controller.dataprovider.StandardBooksStack;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.testng.Assert.*;

public class AddBookCommandTest {
    @BeforeTest
    public void resetLibrary() {
        ArrayList<CustomBook> books = Library.getInstance().getBooks();
        books.forEach(b -> {
            try {
                Library.getInstance().removeBook(b);
            } catch (ModelException ignored) {
            }
        });

        StandardBooksStack.get().forEach(b -> {
            try {
                Library.getInstance().addBook(b);
            } catch (ModelException ignored) {
            }
        });
    }

    @Test(groups = "valid tests", dataProvider = "addbook valid command",
            dataProviderClass = AddBookDataProvider.class)
    public void testExecute_valid(String command,
                                  ArrayList<CustomBook> expected) {
        AddBookCommand addBookCommand = new AddBookCommand();
        try {
            ArrayList<CustomBook> actual = addBookCommand.execute(command);
            assertEquals(actual, expected);
        } catch (ControllerException ex) {
            fail(ex.toString());
        }
    }

    @Test(groups = "exception tests", dataProvider = "addbook invalid command",
            dataProviderClass = AddBookDataProvider.class,
            expectedExceptions = ControllerException.class)
    public void testExecute_test(String command) throws ControllerException {
        AddBookCommand addBookCommand = new AddBookCommand();
        addBookCommand.execute(command);
    }
}