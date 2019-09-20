package www.book.dao;

import www.book.api.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {

    public AuthorDAOImpl() {
    }

    @PersistenceContext(unitName = "book-hibernate")
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    @Override
    public List<Author> listAll() {
        TypedQuery<Author> query = entityManager.createQuery("SELECT b FROM Author b", Author.class);
        return query.getResultList();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    @Override
    public Author get(long id) {
        return entityManager.find(Author.class, id);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public void add(Author author) {
        entityManager.persist(author);
        entityManager.flush();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public void delete(long id) {
        Author author = get(id);
        entityManager.remove(author);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public void update(Author author) {
        entityManager.merge(author);
    }

}
