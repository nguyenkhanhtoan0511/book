package www.booking.service;

import www.booking.api.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> listAll();

    Booking get(long id);

    void add(Booking booking);

    void update(long id, Booking booking);

    void delete(long id);
}
