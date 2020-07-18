package mishamba.day6.controller.command.impl;

import com.mishamba.day6.controller.command.impl.SortByAuthorsCommand;
import com.mishamba.day6.controller.exception.ControllerException;
import com.mishamba.day6.model.entity.CustomBook;
import com.mishamba.day6.model.entity.Library;
import com.mishamba.day6.model.exception.ModelException;
import mishamba.day6.controller.dataprovider.SortByAuthorsDataProvider;
import mishamba.day6.controller.dataprovider.SortByPagesDataProvider;
import mishamba.day6.controller.dataprovider.StandardBooksStack;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class SortByAuthorsCommandTest {

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

    @Test(dataProvider = "sortbyauthors sorted order",
            dataProviderClass = SortByAuthorsDataProvider.class)
    public void testExecute_valid(ArrayList<CustomBook> expected) {
        SortByAuthorsCommand sortByAuthorsCommand = new SortByAuthorsCommand();
        try {
            ArrayList<CustomBook> actual = sortByAuthorsCommand.execute(" ");
            assertEquals(actual, expected);
        } catch (ControllerException ex) {
            fail(ex.toString());
        }
    }
}