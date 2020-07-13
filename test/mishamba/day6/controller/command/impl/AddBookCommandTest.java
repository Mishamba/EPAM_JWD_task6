package mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.impl.AddBookCommand;
import com.mishamba.day6.controller.exception.ControllerException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AddBookCommandTest {

    @Test(groups = "valid tests")
    @Parameters(value = "valid command")
    public void testExecute_valid(String command) {
        AddBookCommand addBookCommand = new AddBookCommand();
        String expected = "book added successfully";
        try {
            String actual = addBookCommand.execute(command);
            assertEquals(actual, expected);
        } catch (ControllerException ex) {
            fail(ex.toString());
        }
    }

    @Test(groups = "invalid tests",
            expectedExceptions = ControllerException.class)
    @Parameters(value = "invalid command")
    public void testExecute_test(String command) throws ControllerException {
        AddBookCommand addBookCommand = new AddBookCommand();
        addBookCommand.execute(command);
    }
}