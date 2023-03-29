package test;

import com.mashape.unirest.http.HttpResponse;
import model.Book;
import model.BookList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import util.BookNormalizer;
import util.Config;
import util.JSONMapper;

import java.util.Arrays;

public abstract class BaseTest {
    protected Logger LOG = LoggerFactory.getLogger(this.getClass());
    protected static final int CORRECT_STATUS = 200;
    protected static final int CREATED = 201;
    protected static final int BAD_REQUEST = 400;
    protected static final int NOT_FOUND = 404;
    protected static final String ERROR_MESSAGE_ID_NO_FOUND = Config.get("error_message_id_no_found");
    protected static final String ERROR_MESSAGE_NAME_IS_REQUIRED = Config.get("error_message_name_is_required");

    protected HttpResponse<String> jsonResponse;
    protected Book responseBook;
    protected String responseBody;
    protected BookList responseBookList;

    protected void checkStatus(int status){
        Assert.assertEquals(status, jsonResponse.getStatus(), "Get response code is incorrect");
    }

    protected void getBookFromResponse() {
        responseBody = jsonResponse.getBody();
        responseBook = JSONMapper.getBookFromJson(responseBody).getBook();
        BookNormalizer.normalizeJsonBook(responseBook);
    }

    protected void getBookList() {
        responseBody = jsonResponse.getBody();
        responseBookList = JSONMapper.getBookListFromJson(responseBody);
        Arrays.stream(responseBookList.getBooks()).forEach(BookNormalizer::normalizeJsonBook);
    }

    protected boolean getResultMessage() {
        responseBody = jsonResponse.getBody();
        return JSONMapper.getResultFromJSON(responseBody);
    }

    protected String getErrorMessage() {
        responseBody = jsonResponse.getBody();
        return JSONMapper.getErrorMessageFromJSON(responseBody);
    }
}
