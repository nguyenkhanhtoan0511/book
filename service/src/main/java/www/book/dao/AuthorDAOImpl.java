package www.book.dao;

import www.book.api.Author;
import www.book.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {

    private EntityManager entityManager;

    public AuthorDAOImpl(EntityManagerUtil entityManagerUtil) {
        this.entityManager = entityManagerUtil.getEntityManager();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    @Override
    public List<Author> listAll() {
        try {
            TypedQuery<Author> query = entityManager.createQuery("SELECT b FROM Author b", Author.class);
            return query.getResultList();
        }catch (Throwable e){
            throw e;
        }
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    @Override
    public Author get(long id) {
        try {
            return entityManager.find(Author.class, id);
        }catch (Throwable e){
            throw e;
        }

    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public void add(Author author) {
        try {
            entityManager.persist(author);
            entityManager.flush();
        }catch (Throwable e){
            throw e;
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public void delete(long id) {
        try {
            Author author = get(id);
            entityManager.remove(author);
        }catch (Throwable e){
            throw e;
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public void update(Author author) {
        try {
            entityManager.merge(author);
        }catch (Throwable e){
            throw e;
        }
    }

}
