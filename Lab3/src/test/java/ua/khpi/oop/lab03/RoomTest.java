package ua.khpi.oop.lab03;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab03.Room;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void shortConstructorCreatesAvailableRoom() {
        Room room = new Room("101", "single", 800.0);

        assertTrue(room.isAvailable());
    }

    @Test
    void bookMakesRoomUnavailable() {
        Room room = new Room("101", "single", 800.0);

        room.book();

        assertFalse(room.isAvailable());
    }

    @Test
    void releaseMakesRoomAvailable() {
        Room room = new Room("101", "single", 800.0);
        room.book();

        room.release();

        assertTrue(room.isAvailable());
    }

    @Test
    void bookingAlreadyBookedRoomThrowsException() {
        Room room = new Room("101", "single", 800.0);
        room.book();

        assertThrows(IllegalStateException.class, room::book);
    }

    @Test
    void roomsWithSameNumberAreEqual() {
        Room first = new Room("101", "single", 800.0);
        Room second = new Room("101", "double", 1400.0);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }
}