package www.booking.dao;

import www.booking.api.Booking;

import java.util.List;

public interface BookingDAO {

    List<Booking> listAll();

    Booking get(long id);

    void add(Booking booking);

    void update(Booking booking);

    void delete(long id);

}
