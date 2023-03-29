package model;

import lombok.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    public String author;
    public String name;
    public boolean isElectronicBook;
    public int year;
    public int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return name == null? book.name == null? true: false :name.equals(book.name)
                && (isElectronicBook == book.isElectronicBook)
                && (year == book.year)
                && author == null? book.getAuthor() == null? true : false : author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, year, isElectronicBook);
    }
}