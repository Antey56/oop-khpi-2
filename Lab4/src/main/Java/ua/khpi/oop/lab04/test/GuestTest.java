package ua.khpi.oop.lab03;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab03.Guest;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    @Test
    void guestWithPhoneHasPhone() {
        Guest guest = new Guest("G-01", "Olena Kovalenko", "+380501234567");

        assertTrue(guest.hasPhone());
    }

    @Test
    void guestWithoutPhoneDoesNotHavePhone() {
        Guest guest = new Guest("G-02", "Mykhailo Bondar");

        assertFalse(guest.hasPhone());
    }

    @Test
    void guestsWithSameIdAreEqual() {
        Guest first = new Guest("G-01", "Olena Kovalenko");
        Guest second = new Guest("G-01", "Olena K.");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void blankGuestIdThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Guest("", "Olena Kovalenko"));
    }
}