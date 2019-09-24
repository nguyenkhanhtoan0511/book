package www.book.service;

import www.book.api.Author;
import www.book.api.Book;

import java.util.List;

public interface BookService {

    List<Book> listAll();

    Book get(long id);

    void add(Book book);

    void update(Book book);

    void deleteWithAuthor(Author author, long id);

}
