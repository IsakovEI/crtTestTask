package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import model.*;
import model.Error;

public class JSONMapper {
    public static ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static String getBookAsJSON(Book book) {
        return objectMapper.writeValueAsString(book);
    }

    @SneakyThrows
    public static BookRoot getBookFromJson(String json) {
        return objectMapper.readValue(json, BookRoot.class);
    }

    @SneakyThrows
    public static BookList getBookListFromJson(String json) {
        return objectMapper.readValue(json, BookList.class);
    }

    @SneakyThrows
    public static String getErrorMessageFromJSON(String json) {
        return objectMapper.readValue(json, Error.class).getError();
    }

    @SneakyThrows
    public static boolean getResultFromJSON(String json) {
        return objectMapper.readValue(json, Result.class).isResult();
    }
}
