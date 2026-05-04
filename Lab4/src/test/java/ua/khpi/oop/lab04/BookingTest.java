package ua.khpi.oop.lab04;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BookingTest {
    private Guest guest;
    private Room room;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        guest = new Guest("G-01", "Olena Kovalenko", "+380501234567");
        room = new Room("101", "single", 800.0);
    }

    @Test(groups = { "smoke", "domain" })
    public void bookingMakesRoomUnavailable() {
        new Booking("B-001", guest, room, 3);

        Assert.assertFalse(room.isAvailable());
    }

    @Test(groups = { "domain" })
    public void totalCostReturnsPriceMultipliedByNights() {
        Booking booking = new Booking("B-001", guest, room, 3);

        Assert.assertEquals(booking.totalCost(), 2400.0);
    }

    @Test(groups = { "domain" })
    public void cancelDeactivatesBookingAndReleasesRoom() {
        Booking booking = new Booking("B-001", guest, room, 3);

        booking.cancel();

        Assert.assertFalse(booking.isActive());
        Assert.assertTrue(room.isAvailable());
    }

    @Test(groups = { "negative" }, expectedExceptions = IllegalArgumentException.class)
    public void negativeNightsThrowException() {
        new Booking("B-001", guest, room, -2);
    }

    @Test(groups = { "domain" })
    public void bookingsWithSameIdAreEqual() {
        Booking first = new Booking("B-001", guest, room, 3, true);
        Booking second = new Booking("B-001", guest, room, 5, false);

        Assert.assertEquals(first, second);
        Assert.assertEquals(first.hashCode(), second.hashCode());
    }
}