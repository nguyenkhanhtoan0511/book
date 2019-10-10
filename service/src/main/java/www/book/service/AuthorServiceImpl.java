package www.book.service;

import www.book.api.Author;
import www.book.dao.AuthorDAO;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private AuthorDAO m_authorDAO;

    public AuthorServiceImpl(AuthorDAO authorDAO) {
        this.m_authorDAO = authorDAO;
    }

    public AuthorDAO getAuthorDAO() {
        return m_authorDAO;
    }

    public void setAuthorDAO(AuthorDAO authorDAO) {
        m_authorDAO = authorDAO;
    }

    @Override
    public Author get(long id) {
        return m_authorDAO.get(id);
    }

    @Override
    public void add(Author author) {
        m_authorDAO.add(author);
    }

    @Override
    public void update(long id, Author author) {
        m_authorDAO.update(author);
    }

    @Override
    public void delete(long id) {
        m_authorDAO.delete(id);
    }

    @Override
    public List<Author> listAll() {
        return m_authorDAO.listAll();
    }
}
