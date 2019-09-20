package www.booking.service;

import www.book.api.Book;

import java.util.List;

public interface BookService {

    List<Book> listAll();

    Book get(long id);

    void add(Book book);

    void update(long id, Book book);

    void delete(long id);
//
//    List<Book> listAllBookByAuthor(long authorId);
//
//    void addBookwithAuthor(long authorId, Book book);

}
