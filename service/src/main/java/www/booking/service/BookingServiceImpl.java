package www.booking.service;

import www.booking.api.Booking;
import www.booking.dao.BookingDAO;

import java.util.Date;
import java.util.List;

public class BookingServiceImpl implements BookingService {

    private BookingDAO m_bookingDAO;

    public BookingServiceImpl(BookingDAO bookingDAO) {
        this.m_bookingDAO = bookingDAO;
    }

    public BookingDAO getBookingDAO() {
        return m_bookingDAO;
    }

    public void setBookingDAO(BookingDAO bookingDAO) {
        m_bookingDAO = bookingDAO;
    }

    public void init() {
        System.out.println("Init BookingServiceImpl");

        Date cDate = new Date();

        Booking book1 = new Booking("ahihi", "success", cDate, (float) 1);
        Booking book2 = new Booking("book2", "dasczx", cDate, (float) 6311.6);
        Booking book3 = new Booking("book3", "house", cDate, (float) 10.2);
        Booking book4 = new Booking("book4", "hibernate", cDate, (float) 60.6);

        add(book1);
        add(book2);
        add(book3);
        add(book4);

        long id = 1;
        Booking bookingID1 = get(id);
        System.out.println("info book id: "+ id + bookingID1.toString());

        Booking book5 = new Booking("aaaaaaaaaa", "hibernate", cDate, (float) 60.6);
        update(1, book5);
//
        delete(2);

        List<Booking> bookings = listAll();
        System.out.println("List Booking -> " + bookings);
    }

    public void destroy() {
        System.out.println("Destroy BookingServiceImpl");
    }

    @Override
    public Booking get(long id) {
        return m_bookingDAO.get(id);
    }

    @Override
    public void add(Booking booking) {
        m_bookingDAO.add(booking);
    }

    @Override
    public void update(long id, Booking booking) {
        Booking bookFromDb = (Booking) get(id);
        bookFromDb.setTitle(booking.getTitle());
        m_bookingDAO.update(bookFromDb);
    }

    @Override
    public void delete(long id) {
        m_bookingDAO.delete(id);
    }

    @Override
    public List<Booking> listAll() {
        return m_bookingDAO.listAll();
    }

}
