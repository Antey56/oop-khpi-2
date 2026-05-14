package ua.khpi.oop.lab08;

import org.testng.annotations.Test;

public class ExceptionTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void bookingWithInvalidNightsThrowsException() {
        Guest guest = new Guest("G-01", "Olena Kovalenko");
        Room room = new StandardRoom("101", 800.0, true);

        new Booking("B-001", guest, room, 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void guestWithBlankNameThrowsException() {
        new Guest("G-01", "");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void roomWithNegativePriceThrowsException() {
        new StandardRoom("101", -800.0, true);
    }
}