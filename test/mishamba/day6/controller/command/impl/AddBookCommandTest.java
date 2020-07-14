package mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.impl.AddBookCommand;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.model.entity.CustomBook;
import mishamba.day6.controller.dataprovider.AddBookDataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class AddBookCommandTest {

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