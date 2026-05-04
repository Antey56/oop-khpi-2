package ua.khpi.oop.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    void bookingShouldMakeRoomUnavailable() {
        Guest guest = new Guest("G-01", "Olena Kovalenko", "+380501234567");
        Room room = new Room("101", "single", 800.0);

        new Booking("B-001", guest, room, 3);

        assertFalse(room.isAvailable());
    }

    @Test
    void totalCostShouldReturnPriceMultipliedByNights() {
        Guest guest = new Guest("G-01", "Olena Kovalenko", "+380501234567");
        Room room = new Room("101", "single", 800.0);
        Booking booking = new Booking("B-001", guest, room, 3);

        assertEquals(2400.0, booking.totalCost());
    }

    @Test
    void cancelShouldDeactivateBookingAndReleaseRoom() {
        Guest guest = new Guest("G-01", "Olena Kovalenko", "+380501234567");
        Room room = new Room("101", "single", 800.0);
        Booking booking = new Booking("B-001", guest, room, 3);

        booking.cancel();

        assertFalse(booking.isActive());
        assertTrue(room.isAvailable());
    }

    @Test
    void bookingsWithSameIdShouldBeEqual() {
        Guest guest = new Guest("G-01", "Olena Kovalenko", "+380501234567");
        Room room = new Room("101", "single", 800.0);

        Booking first = new Booking("B-001", guest, room, 3, true);
        Booking second = new Booking("B-001", guest, room, 5, false);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }
}