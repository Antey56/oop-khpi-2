package ua.khpi.oop.lab04;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RoomTest {

    @Test(groups = { "smoke", "domain" })
    public void shortConstructorCreatesAvailableRoom() {
        Room room = new Room("101", "single", 800.0);

        Assert.assertTrue(room.isAvailable());
    }

    @Test(groups = { "domain" })
    public void bookMakesRoomUnavailable() {
        Room room = new Room("101", "single", 800.0);

        room.book();

        Assert.assertFalse(room.isAvailable());
    }

    @Test(groups = { "domain" })
    public void releaseMakesRoomAvailable() {
        Room room = new Room("101", "single", 800.0);
        room.book();

        room.release();

        Assert.assertTrue(room.isAvailable());
    }

    @Test(groups = { "negative" }, expectedExceptions = IllegalStateException.class)
    public void bookingAlreadyBookedRoomThrowsException() {
        Room room = new Room("101", "single", 800.0);

        room.book();
        room.book();
    }

    @Test(groups = { "domain" })
    public void roomsWithSameNumberAreEqual() {
        Room first = new Room("101", "single", 800.0);
        Room second = new Room("101", "double", 1400.0);

        Assert.assertEquals(first, second);
        Assert.assertEquals(first.hashCode(), second.hashCode());
    }

    @Test(groups = { "negative" }, expectedExceptions = IllegalArgumentException.class)
    public void roomWithNegativePriceThrowsException() {
        new Room("101", "single", -100.0);
    }
}