package ua.khpi.oop.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    @Test
    void guestWithPhoneShouldReturnTrue() {
        Guest guest = new Guest("G-01", "Olena Kovalenko", "+380501234567");

        assertTrue(guest.hasPhone());
    }

    @Test
    void guestWithoutPhoneShouldReturnFalse() {
        Guest guest = new Guest("G-02", "Mykhailo Bondar");

        assertFalse(guest.hasPhone());
    }

    @Test
    void guestsWithSameIdShouldBeEqual() {
        Guest first = new Guest("G-01", "Olena Kovalenko", "+380501234567");
        Guest second = new Guest("G-01", "Olena K.", "+380509999999");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }
}