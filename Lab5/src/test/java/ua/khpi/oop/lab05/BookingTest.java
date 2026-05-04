package ua.khpi.oop.lab05;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingTest {

    @Test
    public void bookingWorksWithStandardRoom() {
        Guest guest = new Guest("G-01", "Olena Kovalenko");
        Room room = new StandardRoom("101", 800.0, true);

        Booking booking = new Booking("B-001", guest, room, 3);

        Assert.assertEquals(booking.totalCost(), 2400.0);
        Assert.assertFalse(room.isAvailable());
    }

    @Test
    public void bookingWorksWithLuxuryRoomPolymorphism() {
        Guest guest = new Guest("G-01", "Olena Kovalenko");
        Room room = new LuxuryRoom("310", 2000.0, true);

        Booking booking = new Booking("B-002", guest, room, 2);

        Assert.assertEquals(booking.totalCost(), 6000.0);
    }

    @Test
    public void cancelBookingReleasesInheritedRoom() {
        Guest guest = new Guest("G-01", "Olena Kovalenko");
        Room room = new FamilyRoom("205", 1200.0, 4);

        Booking booking = new Booking("B-003", guest, room, 2);
        booking.cancel();

        Assert.assertTrue(room.isAvailable());
        Assert.assertFalse(booking.isActive());
    }
}