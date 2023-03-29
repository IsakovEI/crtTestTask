package test.nestedTests;

import model.Book;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;
import util.*;

import java.util.Arrays;

public class PostTest extends BaseTest {
    private String book1Name = "book1" + RandomDataGenerator.generateText();
    private String book2Name = "book2" + RandomDataGenerator.generateText();
    private String book3Name = "book3" + RandomDataGenerator.generateText();
    private String book4Name = "book4" + RandomDataGenerator.generateText();

    private Book book1 = Book.builder().name(book1Name).build();
    private Book book2 = Book.builder().name(book2Name).isElectronicBook(true).build();
    private Book book3 = Book.builder().name(book3Name).isElectronicBook(false).build();
    private Book book4 = Book.builder()
            .name(book4Name)
            .year(2000)
            .author("book4Author")
            .isElectronicBook(false)
            .build();
    private Book book5 = Book.builder()
            .author("book5Author")
            .build();

    private int booksCount;
    private Book addedBook;

    @Test
    public void addNewBookTest() {

        LOG.info("Step 1. API get books count");
        jsonResponse = ApiRequestServices.getAllBooks();
        checkStatus(CORRECT_STATUS);
        getBookList();
        booksCount = responseBookList.getBooks().length;
        LOG.info("Books count:" + booksCount);

        LOG.info("Step 2. API Add new book1");
        jsonResponse = ApiRequestServices.addNewBookByName(book1Name);
        checkStatus(CREATED);
        getBookFromResponse();
        Assert.assertEquals(book1, responseBook, "Book1 in response is incorrect");

        LOG.info("Step 3. API get books count");
        jsonResponse = ApiRequestServices.getAllBooks();
        checkStatus(CORRECT_STATUS);
        getBookList();
        Assert.assertEquals(++booksCount, responseBookList.getBooks().length, "Count of books is incorrect");

        LOG.info("Step 4. Check added book1");
        addedBook = Arrays.stream(responseBookList.getBooks()).filter(b -> b.getName().equals(book1Name)).findFirst().get();
        Assert.assertNotNull(addedBook, "Added book not found");
        Assert.assertEquals(addedBook, book1, "Books not equals");

        LOG.info("Step 5. API Add new book2  with isElectronicBook(true)");
        jsonResponse = ApiRequestServices.addNewBook(book2);
        checkStatus(CREATED);
        getBookFromResponse();
        Assert.assertEquals(book2, responseBook, "Book2 in response is incorrect");

        LOG.info("Step 6. API get books count");
        jsonResponse = ApiRequestServices.getAllBooks();
        checkStatus(CORRECT_STATUS);
        getBookList();
        Assert.assertEquals(++booksCount, responseBookList.getBooks().length, "Count of books is incorrect");

        LOG.info("Step 7. Check added book2");
        addedBook = Arrays.stream(responseBookList.getBooks()).filter(b -> b.getName().equals(book2Name)).findFirst().get();
        Assert.assertNotNull(addedBook, "Added book not found");
        Assert.assertEquals(addedBook, book2, "Books not equals");

        LOG.info("Step 8. API Add new book  with isElectronicBook(false)");
        jsonResponse = ApiRequestServices.addNewBook(book3);
        checkStatus(CREATED);
        getBookFromResponse();
        Assert.assertEquals(book3, responseBook, "Book3 in response is incorrect");

        LOG.info("Step 9. API get books count");
        jsonResponse = ApiRequestServices.getAllBooks();
        checkStatus(CORRECT_STATUS);
        getBookList();
        Assert.assertEquals(++booksCount, responseBookList.getBooks().length, "Count of books is incorrect");

        LOG.info("Step 10. Check added book3");
        addedBook = Arrays.stream(responseBookList.getBooks()).filter(b -> b.getName().equals(book3Name)).findFirst().get();
        Assert.assertNotNull(addedBook, "Added book not found");
        Assert.assertEquals(addedBook, book3, "Books not equals");

        LOG.info("Step 11. API Add new book with allFields");
        jsonResponse = ApiRequestServices.addNewBook(book4);
        checkStatus(CREATED);
        getBookFromResponse();
        Assert.assertEquals(book4, responseBook, "Book4 in response is incorrect");

        LOG.info("Step 12. API get books count");
        jsonResponse = ApiRequestServices.getAllBooks();
        checkStatus(CORRECT_STATUS);
        getBookList();
        Assert.assertEquals(++booksCount, responseBookList.getBooks().length, "Count of books is incorrect");

        LOG.info("Step 13. Check added book4");
        addedBook = Arrays.stream(responseBookList.getBooks()).filter(b -> b.getName().equals(book4Name)).findFirst().get();
        Assert.assertNotNull(addedBook, "Added book not found");
        Assert.assertEquals(addedBook, book4, "Books not equals");

        LOG.info("Step 14. API Add book without name field");
        jsonResponse = ApiRequestServices.addNewBook(book5);
        checkStatus(BAD_REQUEST);
        String responseErrorMessage = getErrorMessage();
        Assert.assertEquals(ERROR_MESSAGE_NAME_IS_REQUIRED, responseErrorMessage, "Error message is incorrect");
    }
}
