package www.book.dao;

import www.book.api.Author;
import www.book.api.Book;
import www.book.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BookDAOImpl implements BookDAO {

    private EntityManager entityManager;

    public BookDAOImpl(EntityManagerUtil entityManagerUtil) {
        this.entityManager = entityManagerUtil.getEntityManager();
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

}
