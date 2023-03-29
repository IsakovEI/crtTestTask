package test.nestedTests;

import model.Book;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;
import util.*;

public class PutTest extends BaseTest {

    private Book updatedBook = JSONMapper.getBookFromJson(MyFileReader.readFile(Config.get("book2"))).getBook();
    private int id = 1;

    @Test
    public void unionReportingWebTest() {

        LOG.info("Step 1. API Update book by id IsElectronicBook(true)");
        updatedBook.setElectronicBook(true);
        jsonResponse = ApiRequestServices.updateBookByID(id, updatedBook);
        checkStatus(CORRECT_STATUS);
        getBookFromResponse();
        Assert.assertEquals(updatedBook, responseBook, "Book in response is incorrect");

        LOG.info("Step 2. API Get updated book by id IsElectronicBook(false)");
        jsonResponse = ApiRequestServices.getBookById(id);
        checkStatus(CORRECT_STATUS);
        getBookFromResponse();
        Assert.assertEquals(updatedBook, responseBook, String.format("Book with ID = %s in response is incorrect", id));

        LOG.info("Step 3. API Update book by id");
        updatedBook.setElectronicBook(false);
        jsonResponse = ApiRequestServices.updateBookByID(id, updatedBook);
        checkStatus(CORRECT_STATUS);
        getBookFromResponse();
        Assert.assertEquals(updatedBook, responseBook, "Book in response is incorrect");

        LOG.info("Step 4. API Get updated book by id");
        jsonResponse = ApiRequestServices.getBookById(id);
        checkStatus(CORRECT_STATUS);
        getBookFromResponse();
        Assert.assertEquals(updatedBook, responseBook, String.format("Book with ID = %s in response is incorrect", id));

        LOG.info("Step 5. API Update book by id Year = 0");
        updatedBook.setYear(0);
        jsonResponse = ApiRequestServices.updateBookByID(id, updatedBook);
        checkStatus(CORRECT_STATUS);
        getBookFromResponse();
        Assert.assertEquals(updatedBook, responseBook, "Book in response is incorrect");

        LOG.info("Step 6. API Get updated book by id");
        jsonResponse = ApiRequestServices.getBookById(id);
        checkStatus(CORRECT_STATUS);
        getBookFromResponse();
        Assert.assertEquals(updatedBook, responseBook, String.format("Book with ID = %s in response is incorrect", id));

        LOG.info("Step 7. API Update book by id Year = 1");
        updatedBook.setYear(1);
        jsonResponse = ApiRequestServices.updateBookByID(id, updatedBook);
        checkStatus(CORRECT_STATUS);
        getBookFromResponse();
        Assert.assertEquals(updatedBook, responseBook, "Book in response is incorrect");

        LOG.info("Step 8. API Get updated book by id");
        jsonResponse = ApiRequestServices.getBookById(id);
        checkStatus(CORRECT_STATUS);
        getBookFromResponse();
        Assert.assertEquals(updatedBook, responseBook, String.format("Book with ID = %s in response is incorrect", id));

        LOG.info("Step 9. API Update book by id Year = -1");
        updatedBook.setYear(-1);
        jsonResponse = ApiRequestServices.updateBookByID(id, updatedBook);
        checkStatus(CORRECT_STATUS);
        getBookFromResponse();
        Assert.assertEquals(updatedBook, responseBook, "Book in response is incorrect");

        LOG.info("Step 10. API Get updated book by id");
        jsonResponse = ApiRequestServices.getBookById(id);
        checkStatus(CORRECT_STATUS);
        getBookFromResponse();
        Assert.assertEquals(updatedBook, responseBook, String.format("Book with ID = %s in response is incorrect", id));
    }
}
