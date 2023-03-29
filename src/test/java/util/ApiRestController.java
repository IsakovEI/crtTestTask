package util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiRestController {
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_VALUE_APPLICATON_JSON = "application/json";

    public static  HttpResponse<String> get(String url) {
        try {
            return Unirest.get(url).asString();
        } catch (UnirestException e) {
            log.info(e.toString());
            return null;
        }
    }

    public static HttpResponse post(String url, String body) {
        try {
            return Unirest.post(url)
                    .header(HEADER_CONTENT_TYPE, HEADER_VALUE_APPLICATON_JSON)
                    .body(body)
                    .asString();
        } catch (UnirestException e) {
            log.info(e.toString());
            return null;
        }
    }

    public static  HttpResponse put(String url, String body) {
        try {
            return Unirest.put(url)
                    .header(HEADER_CONTENT_TYPE, HEADER_VALUE_APPLICATON_JSON)
                    .body(body)
                    .asString();
        } catch (UnirestException e) {
            log.info(e.toString());
            return null;
        }
    }

    public static  HttpResponse<String> delete(String url) {
        try {
            return Unirest.delete(url).asString();
        } catch (UnirestException e) {
            log.info(e.toString());
            return null;
        }
    }
}
