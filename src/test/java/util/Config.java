package util;

import org.json.JSONObject;

public class Config {
    private static final String CONFIG = MyFileReader.readFile("src/test/resources/config.json");

    public static String get(String paramName) {
        return new JSONObject(Config.CONFIG).get(paramName).toString();
    }
}
