package www.book.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerUtil {

    @PersistenceContext(unitName = "book-hibernate")
    private EntityManager entityManager;

    public EntityManagerUtil() {
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
