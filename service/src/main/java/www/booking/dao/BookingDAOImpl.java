package www.booking.dao;


import www.booking.api.Booking;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BookingDAOImpl implements BookingDAO {

    public BookingDAOImpl() {
    }

    @PersistenceContext(unitName = "booking-hibernate")
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    @Override
    public List<Booking> listAll() {
        TypedQuery<Booking> query = entityManager.createQuery("SELECT b FROM booking b", Booking.class);
        return query.getResultList();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    @Override
    public Booking get(long id) {
        return entityManager.find(Booking.class, id);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public void add(Booking booking) {
        entityManager.persist(booking);
        entityManager.flush();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public void delete(long id) {
        Booking booking = get(id);
        entityManager.remove(booking);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public void update(Booking booking) {
        entityManager.merge(booking);
    }

}
