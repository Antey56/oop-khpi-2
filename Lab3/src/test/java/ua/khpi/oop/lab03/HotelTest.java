package ua.khpi.oop.lab03;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab03.Hotel;
import ua.khpi.oop.lab03.Room;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    @Test
    void constructorStoresNameAndCapacity() {
        Hotel hotel = new Hotel("Kharkiv Palace", 3);

        assertEquals("Kharkiv Palace", hotel.getName());
        assertEquals(0, hotel.getRoomCount());
    }

    @Test
    void constructorWithInvalidNameThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hotel("", 3));
    }

    @Test
    void constructorWithInvalidCapacityThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hotel("Hotel", 0));
    }

    @Test
    void addRoomIncreasesRoomCount() {
        Hotel hotel = new Hotel("Hotel", 2);
        Room room = new Room("101", "single", 800.0);

        hotel.addRoom(room);

        assertEquals(1, hotel.getRoomCount());
    }

    @Test
    void addNullRoomThrowsException() {
        Hotel hotel = new Hotel("Hotel", 2);

        assertThrows(IllegalArgumentException.class,
                () -> hotel.addRoom(null));
    }

    @Test
    void addingRoomOverCapacityThrowsException() {
        Hotel hotel = new Hotel("Hotel", 1);

        hotel.addRoom(new Room("101", "single", 800.0));

        assertThrows(IllegalStateException.class,
                () -> hotel.addRoom(new Room("102", "double", 1200.0)));
    }

    @Test
    void findAvailableRoomReturnsFirstAvailableRoom() {
        Hotel hotel = new Hotel("Hotel", 2);

        Room room1 = new Room("101", "single", 800.0);
        Room room2 = new Room("102", "double", 1200.0);

        hotel.addRoom(room1);
        hotel.addRoom(room2);

        room1.book();

        Room result = hotel.findAvailableRoom();

        assertEquals(room2, result);
    }

    @Test
    void findAvailableRoomReturnsNullIfNoneAvailable() {
        Hotel hotel = new Hotel("Hotel", 1);

        Room room = new Room("101", "single", 800.0);
        hotel.addRoom(room);

        room.book();

        assertNull(hotel.findAvailableRoom());
    }
}