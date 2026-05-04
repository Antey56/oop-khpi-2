package ua.khpi.oop.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void newRoomShouldBeAvailableByDefault() {
        Room room = new Room("101", "single", 800.0);

        assertTrue(room.isAvailable());
    }

    @Test
    void bookShouldMakeRoomUnavailable() {
        Room room = new Room("101", "single", 800.0);

        room.book();

        assertFalse(room.isAvailable());
    }

    @Test
    void releaseShouldMakeRoomAvailable() {
        Room room = new Room("101", "single", 800.0);
        room.book();

        room.release();

        assertTrue(room.isAvailable());
    }

    @Test
    void roomsWithSameNumberShouldBeEqual() {
        Room first = new Room("101", "single", 800.0);
        Room second = new Room("101", "double", 1200.0);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }
}