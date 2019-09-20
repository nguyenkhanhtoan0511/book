package www.book.rest;

import www.book.api.Book;
import www.book.service.BookService;

import java.util.List;


public class BookRestImpl implements BookRest{

    private BookService m_bookService;

    public BookRestImpl(BookService bookService) {
        m_bookService = bookService;
    }

    @Override

    public List<Book> listAll() {
        return m_bookService.listAll();
    }

    @Override

    public Book get(long id) {
        return m_bookService.get(id);
    }

    @Override

    public void add(Book book) {
        m_bookService.add(book);
    }

    @Override

    public void update(long id, Book book) {
        m_bookService.update(id, book);
    }

    @Override
    public void delete(long id) {
        m_bookService.delete(id);
    }

    @Override

    public List<Book> listAllBookByAuthor(long authorId) {
        return m_bookService.listAllBookByAuthor(authorId);
    }

    @Override
    public void addBookWithAuthor(long authorId, Book book) {
        m_bookService.addBookWithAuthor(authorId, book);
    }
}
