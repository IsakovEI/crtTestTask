package util;

import com.mashape.unirest.http.HttpResponse;
import model.Book;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiRequestServices {
    private static final String HOST = Config.get("host");

    private static String getUrl(String variable) {
        String endPoint = String.format("%s%s", HOST, variable);
        log.info(endPoint);
        return endPoint;
    }

    public static HttpResponse<String> getAllBooks() {
        return ApiRestController.get(getUrl(""));
    }

    public static HttpResponse<String> getBookById(int id) {
        return ApiRestController.get(getUrl(getVariable(String.valueOf(id))));
    }

    public static HttpResponse addNewBookByName(String name) {
        Book newBook = Book.builder().name(name).build();
        return addNewBook(newBook);
    }

    public static HttpResponse addNewBook(Book book) {
        String bookAsJson = JSONMapper.getBookAsJSON(book);
        bookAsJson = bookAsJson.replaceAll("\"\\w*\":null,", "").replaceAll(",\"\\w*\":null", "");
        return ApiRestController.post(getUrl(""), bookAsJson);
    }

    public static HttpResponse updateBookByID(int id, Book book) {
        String bookAsJson = JSONMapper.getBookAsJSON(book);
        return ApiRestController.put(getUrl(getVariable(String.valueOf(id))), bookAsJson);
    }

    public static HttpResponse deleteBookByID(int id) {
        return ApiRestController.delete(getUrl(getVariable(String.valueOf(id))));
    }

    private static String getVariable(String subStr){
        return "/" + subStr;
    }
}
