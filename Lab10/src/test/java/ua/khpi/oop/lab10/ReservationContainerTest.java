package ua.khpi.oop.lab10;

import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class ReservationContainerTest {
    @Test(groups = {"container", "smoke"})
    public void shouldAddItemsAndReturnSize() {
        ReservationContainer<String> container = new ReservationContainer<>();

        assertTrue(container.isEmpty());
        container.add("first");
        container.add("second");

        assertFalse(container.isEmpty());
        assertEquals(container.size(), 2);
        assertEquals(container.get(0), "first");
        assertEquals(container.get(1), "second");
    }

    @Test(groups = {"container"})
    public void shouldRemoveItemAndShiftElements() {
        ReservationContainer<String> container = new ReservationContainer<>();
        container.add("first");
        container.add("second");
        container.add("third");

        String removed = container.remove(1);

        assertEquals(removed, "second");
        assertEquals(container.size(), 2);
        assertEquals(container.get(0), "first");
        assertEquals(container.get(1), "third");
    }

    @Test(groups = {"container"})
    public void shouldGrowAutomatically() {
        ReservationContainer<Integer> container = new ReservationContainer<>(2);

        container.add(10);
        container.add(20);
        container.add(30);

        assertEquals(container.size(), 3);
        assertEquals(container.capacity(), 4);
    }

    @Test(groups = {"container"})
    public void shouldIterateWithForEach() {
        ReservationContainer<String> container = new ReservationContainer<>();
        container.add("A");
        container.add("B");
        container.add("C");

        String result = "";
        for (String item : container) {
            result += item;
        }

        assertEquals(result, "ABC");
    }

    @Test(groups = {"container"})
    public void shouldWorkWithReservationRecords() {
        Customer customer = new Customer("C-001", "Olena Kovalenko");
        Room room = new StandardRoom("101", 800.0, true);
        Reservation reservation = new Reservation("R-001", customer, room, 2);
        ReservationRecord<Reservation, String> record = new ReservationRecord<>(reservation, "confirmed");

        ReservationContainer<ReservationRecord<Reservation, String>> container = new ReservationContainer<>();
        container.add(record);

        assertEquals(container.size(), 1);
        assertEquals(container.get(0).getMetadata(), "confirmed");
        assertEquals(container.get(0).getReservation().getReservationId(), "R-001");
    }

    @Test(groups = {"negative"}, expectedExceptions = IndexOutOfBoundsException.class)
    public void shouldRejectIncorrectIndex() {
        ReservationContainer<String> container = new ReservationContainer<>();
        container.get(0);
    }

    @Test(groups = {"negative"}, expectedExceptions = NoSuchElementException.class)
    public void shouldRejectIteratorNextWhenNoElements() {
        ReservationContainer<String> container = new ReservationContainer<>();
        Iterator<String> iterator = container.iterator();
        iterator.next();
    }
}
