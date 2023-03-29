package test.nestedTests;

import model.Book;
import model.BookList;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;
import util.ApiRequestServices;
import util.JSONMapper;

import java.util.Arrays;

public class DeleteTest extends BaseTest {

    private int booksCount;
    private BookList responseBookList;
    private int id;
    private int incorrectId;
    private boolean responseResultMessage;

    @Test
    public void unionReportingWebTest() {

        LOG.info("Step 1. API get books count");
        jsonResponse = ApiRequestServices.getAllBooks();
        checkStatus(CORRECT_STATUS);
        responseBody = jsonResponse.getBody();
        responseBookList = JSONMapper.getBookListFromJson(responseBody);
        booksCount = responseBookList.getBooks().length;
        incorrectId = booksCount * 10;
        if (booksCount == 0) {
            LOG.info("Books count = 0");
            throw new RuntimeException("Books count = 0");
        }
        id = responseBookList.getBooks()[0].getId();

        LOG.info("Step 2. API Delete book by id");
        jsonResponse = ApiRequestServices.deleteBookByID(id);
        checkStatus(CORRECT_STATUS);
        responseResultMessage = getResultMessage();
        Assert.assertTrue(responseResultMessage, "Result is incorrect");

        LOG.info("Step 3. API get books count");
        jsonResponse = ApiRequestServices.getAllBooks();
        checkStatus(CORRECT_STATUS);
        responseBody = jsonResponse.getBody();
        responseBookList = JSONMapper.getBookListFromJson(responseBody);
        Assert.assertEquals(booksCount - 1, responseBookList.getBooks().length, "Count of books is incorrect");

        LOG.info("Step 4. Check deleted book");
        boolean isDeletedBookExists = Arrays.stream(responseBookList.getBooks()).anyMatch(b -> b.getId() == id);
        Assert.assertFalse(isDeletedBookExists, "Deleted book found");
    }
}
