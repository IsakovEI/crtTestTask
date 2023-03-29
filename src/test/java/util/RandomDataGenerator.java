package util;

public class RandomDataGenerator {

    public static String generateText() {
        int length = (int) (Math.random() * 10) + 10;
        char[] charArray = new char[length];

        for (int i = 0; i < length; i++) {
            charArray[i] = (char) ((int) (Math.random() * 25) + 97);
        }
        charArray[0] = Character.toUpperCase(charArray[0]);
        return new String(charArray);
    }

    public static int generateDate() {
        return (int) (Math.random() * 100) + 1900;
    }
}
