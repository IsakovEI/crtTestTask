package util;

import model.Book;

public class BookNormalizer {
    public static void normalizeJsonBook(Book book) {
        if (book.getName().equals("")) {
            book.setName(null);
        }
        if(book.getAuthor().equals("")){
            book.setAuthor(null);
        }
    }
}
