package www.book.dao;

import www.book.api.Author;
import www.book.api.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BookDAOImpl implements BookDAO {

    public BookDAOImpl() {
    }

    @PersistenceContext(unitName = "book-hibernate")
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    @Override
    public List<Book> listAll() {
        try {
            TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
            return query.getResultList();
        }catch (Throwable e){
            throw e;
        }
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    @Override
    public Book get(long id) {
        try {
            return entityManager.find(Book.class, id);
        }catch (Throwable e){
            throw e;
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public void add(Book book) {
        try {
            entityManager.persist(book);
            entityManager.flush();
        }catch (Throwable e){
            throw e;
        }

    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public void update(Book book) {
        try {
            entityManager.merge(book);
        }catch (Throwable e){
            throw e;
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public void delete(long id) {
        try {
            Book book = get(id);
            entityManager.remove(book);
        }catch (Throwable e){
            throw e;
        }
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    @Override
    public List<Book> listAllBookByAuthor(long authorId) {
        try {
            Author author = entityManager.find(Author.class, authorId);
            return author.getBooks();
        }catch (Throwable e){
            throw e;
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public void addBookWithAuthor(long authorId, Book book) {
        try {
            Author author = entityManager.find(Author.class, authorId);
            author.getBooks().add(book);
            entityManager.merge(author);
        }catch (Throwable e){
            throw e;
        }
    }

}
