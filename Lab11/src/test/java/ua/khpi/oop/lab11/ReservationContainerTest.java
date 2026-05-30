package ua.khpi.oop.lab10;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ReservationContainerTest {

    @Test
    void testInitialState() {
        ReservationContainer<String> container = new ReservationContainer<>();

        assertTrue(container.isEmpty());
        assertEquals(0, container.size());
        assertEquals(10, container.capacity());
    }

    @Test
    void testAddAndGet() {
        ReservationContainer<String> container = new ReservationContainer<>();

        container.add("First");
        container.add("Second");

        assertFalse(container.isEmpty());
        assertEquals(2, container.size());
        assertEquals("First", container.get(0));
        assertEquals("Second", container.get(1));
    }

    @Test
    void testRemove() {
        ReservationContainer<String> container = new ReservationContainer<>();

        container.add("A");
        container.add("B");
        container.add("C");

        String removed = container.remove(1);

        assertEquals("B", removed);
        assertEquals(2, container.size());
        assertEquals("A", container.get(0));
        assertEquals("C", container.get(1));
    }

    @Test
    void testIteratorDirectly() {
        ReservationContainer<Integer> container = new ReservationContainer<>();

        container.add(10);
        container.add(20);

        Iterator<Integer> iterator = container.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(10, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(20, iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testForEachLoop() {
        ReservationContainer<Integer> container = new ReservationContainer<>();

        container.add(1);
        container.add(2);
        container.add(3);

        int sum = 0;

        for (Integer number : container) {
            sum += number;
        }

        assertEquals(6, sum);
    }

    @Test
    void testCapacityGrowth() {
        ReservationContainer<Integer> container = new ReservationContainer<>(2);

        container.add(1);
        container.add(2);

        assertEquals(2, container.capacity());

        container.add(3);

        assertEquals(3, container.size());
        assertEquals(4, container.capacity());
    }

    @Test
    void testSetContainsIndexOfAndClear() {
        ReservationContainer<String> container = new ReservationContainer<>();

        container.add("Old");
        container.add("Second");

        String previous = container.set(0, "New");

        assertEquals("Old", previous);
        assertEquals("New", container.get(0));
        assertTrue(container.contains("Second"));
        assertEquals(1, container.indexOf("Second"));

        container.clear();

        assertTrue(container.isEmpty());
        assertEquals(0, container.size());
    }

    @Test
    void testOutOfBounds() {
        ReservationContainer<String> container = new ReservationContainer<>();

        container.add("Test");

        assertThrows(IndexOutOfBoundsException.class, () -> container.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(5));
    }

    @Test
    void testInvalidCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new ReservationContainer<>(0));
        assertThrows(IllegalArgumentException.class, () -> new ReservationContainer<>(-1));
    }
}
