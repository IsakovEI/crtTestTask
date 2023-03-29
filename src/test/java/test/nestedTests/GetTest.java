package test.nestedTests;

import model.Book;
import model.BookList;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;
import util.*;
import java.util.Arrays;

public class GetTest extends BaseTest {
    private Book book1 = JSONMapper.getBookFromJson(MyFileReader.readFile(Config.get("book1"))).getBook();
    private Book book2 = JSONMapper.getBookFromJson(MyFileReader.readFile(Config.get("book2"))).getBook();
    private int firstId = 1;
    private int secondId = 2;
    private int incorrectId;
    private String errorMessage;

    @Test
    public void getAllBooksTest() {
        LOG.info("Step 1. API Get all books");
        jsonResponse = ApiRequestServices.getAllBooks();
        checkStatus(CORRECT_STATUS);
        responseBody = jsonResponse.getBody();
        BookList responseBookList = JSONMapper.getBookListFromJson(responseBody);
        incorrectId = responseBookList.books.length * 10;
        //Assert.assertEquals(responseBookList.getBooks().length, 2, "Count of books in response is incorrect"); // Опционально, если мы знаем, что в базе только 2 книги
        Assert.assertEquals(book1, Arrays.stream(responseBookList.getBooks()).filter(book -> book.getId() == book1.getId()).findFirst().get(), "First book in response is incorrect");
        Assert.assertEquals(book2, Arrays.stream(responseBookList.getBooks()).filter(book -> book.getId() == book2.getId()).findFirst().get(), "Second book in response is incorrect");

        LOG.info("Step 2. API Get book by id = " + firstId);
        jsonResponse = ApiRequestServices.getBookById(firstId);
        checkStatus(CORRECT_STATUS);
        getBookFromResponse();
        Assert.assertEquals(book1, responseBook, String.format("Book with ID = %s in response is incorrect", firstId));

        LOG.info("Step 3. API Get book by id = " + secondId);
        jsonResponse = ApiRequestServices.getBookById(secondId);
        checkStatus(CORRECT_STATUS);
        getBookFromResponse();
        Assert.assertEquals(book2, responseBook, String.format("Book with ID = %s in response is incorrect", secondId));

        LOG.info("Step 4. API Get book by id = " + incorrectId);
        errorMessage = ERROR_MESSAGE_ID_NO_FOUND.replace("<id>", String.valueOf(incorrectId));
        jsonResponse = ApiRequestServices.getBookById(incorrectId);
        checkStatus(NOT_FOUND);
        String responseErrorMessage = getErrorMessage();
        Assert.assertEquals(errorMessage, responseErrorMessage, "Error message is incorrect");
    }
}
