package ua.khpi.oop.lab15;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SynchronizedHotelRoomPoolTest {

    @Test
    public void booksOneRoomSafely() {
        SynchronizedHotelRoomPool pool = new SynchronizedHotelRoomPool(List.of(new StandardRoom("101", 80.0, true)));
        boolean result = pool.tryBook(request("REQ-1"));

        assertTrue(result);
        assertEquals(pool.getReservationCount(), 1);
        assertEquals(pool.getAvailableRoomCount(), 0);
    }

    @Test
    public void rejectsBookingWhenNoRoomsAvailable() {
        SynchronizedHotelRoomPool pool = new SynchronizedHotelRoomPool(List.of(new StandardRoom("101", 80.0, true)));

        assertTrue(pool.tryBook(request("REQ-1")));
        assertFalse(pool.tryBook(request("REQ-2")));
        assertEquals(pool.getReservationCount(), 1);
    }

    @Test
    public void parallelSafeScenarioDoesNotExceedRoomCount() throws InterruptedException {
        int roomCount = 20;
        int threadCount = 4;
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < roomCount; i++) {
            rooms.add(new StandardRoom("S" + i, 80.0, true));
        }

        SynchronizedHotelRoomPool pool = new SynchronizedHotelRoomPool(rooms);
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            List<BookingRequest> requests = new ArrayList<>();
            for (int j = 0; j < 100; j++) {
                requests.add(request("T" + i + "-R" + j));
            }
            threads[i] = new Thread(new BookingTask(requests, pool));
        }

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        assertEquals(pool.getReservationCount(), roomCount);
        assertEquals(pool.getAvailableRoomCount(), 0);
    }

    private BookingRequest request(String id) {
        return new BookingRequest(
                id,
                new Customer("Test Guest", "guest@example.com"),
                "Standard",
                LocalDate.of(2026, 6, 1),
                LocalDate.of(2026, 6, 2)
        );
    }
}
