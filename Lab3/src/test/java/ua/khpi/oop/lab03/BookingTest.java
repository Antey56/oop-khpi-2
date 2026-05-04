package ua.khpi.oop.lab03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab03.Booking;
import ua.khpi.oop.lab03.Guest;
import ua.khpi.oop.lab03.Room;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    private Guest guest;
    private Room room;

    @BeforeEach
    void setUp() {
        guest = new Guest("G-01", "Olena Kovalenko", "+380501234567");
        room = new Room("101", "single", 800.0);
    }

    @Test
    void bookingMakesRoomUnavailable() {
        new Booking("B-001", guest, room, 3);

        assertFalse(room.isAvailable());
    }

    @Test
    void totalCostReturnsPriceMultipliedByNights() {
        Booking booking = new Booking("B-001", guest, room, 3);

        assertEquals(2400.0, booking.totalCost());
    }

    @Test
    void cancelDeactivatesBookingAndReleasesRoom() {
        Booking booking = new Booking("B-001", guest, room, 3);

        booking.cancel();

        assertFalse(booking.isActive());
        assertTrue(room.isAvailable());
    }

    @Test
    void negativeNightsThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Booking("B-001", guest, room, -2));
    }

    @Test
    void bookingsWithSameIdAreEqual() {
        Booking first = new Booking("B-001", guest, room, 3, true);
        Booking second = new Booking("B-001", guest, room, 5, false);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }
}