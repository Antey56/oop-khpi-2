package ua.khpi.oop.lab04;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HotelTest {
    private Hotel hotel;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        hotel = new Hotel("Kharkiv Palace", 3);
    }

    @Test(groups = { "smoke", "domain" })
    public void constructorStoresNameAndCapacity() {
        Assert.assertEquals(hotel.getName(), "Kharkiv Palace");
        Assert.assertEquals(hotel.getRoomCount(), 0);
    }

    @Test(groups = { "negative" }, expectedExceptions = IllegalArgumentException.class)
    public void constructorWithInvalidNameThrowsException() {
        new Hotel("", 3);
    }

    @Test(groups = { "negative" }, expectedExceptions = IllegalArgumentException.class)
    public void constructorWithInvalidCapacityThrowsException() {
        new Hotel("Hotel", 0);
    }

    @Test(groups = { "domain" })
    public void addRoomIncreasesRoomCount() {
        Room room = new Room("101", "single", 800.0);

        hotel.addRoom(room);

        Assert.assertEquals(hotel.getRoomCount(), 1);
    }

    @Test(groups = { "negative" }, expectedExceptions = IllegalArgumentException.class)
    public void addNullRoomThrowsException() {
        hotel.addRoom(null);
    }

    @Test(groups = { "negative" }, expectedExceptions = IllegalStateException.class)
    public void addingRoomOverCapacityThrowsException() {
        hotel.addRoom(new Room("101", "single", 800.0));
        hotel.addRoom(new Room("102", "double", 1200.0));
        hotel.addRoom(new Room("103", "suite", 2500.0));

        hotel.addRoom(new Room("104", "single", 900.0));
    }

    @Test(groups = { "domain" })
    public void findAvailableRoomReturnsFirstAvailableRoom() {
        Room room1 = new Room("101", "single", 800.0);
        Room room2 = new Room("102", "double", 1200.0);

        hotel.addRoom(room1);
        hotel.addRoom(room2);

        room1.book();

        Room result = hotel.findAvailableRoom();

        Assert.assertEquals(result, room2);
    }

    @Test(groups = { "domain" })
    public void findAvailableRoomReturnsNullIfNoneAvailable() {
        Room room = new Room("101", "single", 800.0);

        hotel.addRoom(room);
        room.book();

        Assert.assertNull(hotel.findAvailableRoom());
    }
}