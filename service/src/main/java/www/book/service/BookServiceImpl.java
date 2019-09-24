package www.book.service;

import www.book.api.Author;
import www.book.api.Book;
import www.book.dao.AuthorDAO;
import www.book.dao.BookDAO;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDAO m_bookDAO;
    private AuthorDAO m_authorDAO;

    public BookServiceImpl(BookDAO bookDAO, AuthorDAO authorDAO) {
        this.m_bookDAO = bookDAO;
        this.m_authorDAO = authorDAO;
    }

    @Override
    public Book get(long id) {
        return m_bookDAO.get(id);
    }

    @Override
    public void add(Book book) {
        m_bookDAO.add(book);
    }

    @Override
    public void update(Book book) {
        m_bookDAO.update(book);
    }

    @Override
    public void deleteWithAuthor(Author author, long id) {
        m_bookDAO.deleteWithAuthor(author, id);
    }

    @Override
    public List<Book> listAll() {
        return m_bookDAO.listAll();
    }

}

