package mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.impl.SortByPagesCommand;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.entity.Library;
import com.mishamba.day6.model.exception.ModelException;
import mishamba.day6.controller.dataprovider.SortByPagesDataProvider;
import mishamba.day6.controller.dataprovider.StandardBooksStack;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class SortByPagesCommandTest {

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

    @Test(dataProvider = "sortbypages sorted order",
            dataProviderClass = SortByPagesDataProvider.class)
    public void testExecute(ArrayList<CustomBook> expected) {
        SortByPagesCommand sortByPagesCommand = new SortByPagesCommand();
        try {
            ArrayList<CustomBook> actual = sortByPagesCommand.execute(" ");
            assertEquals(actual, expected);
        } catch (ControllerException ex) {
            fail(ex.toString());
        }
    }
}