package util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class MyFileReader {

    public static String readFile(String src) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(src)))) {
            while (br.ready()) {
                sb.append(br.readLine());
            }
        } catch (IOException e) {
            log.info(e.toString());
            e.printStackTrace();
        }
        return sb.toString();
    }
}
