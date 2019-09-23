package www.book.dao;

import www.book.api.Book;

import java.util.List;

public interface BookDAO {

    List<Book> listAll();

    Book get(long id);

    void add(Book book);

    void update(Book book);

    void delete(long id);

}
