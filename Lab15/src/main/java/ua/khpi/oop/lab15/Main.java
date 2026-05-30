package ua.khpi.oop.lab15;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int THREAD_COUNT = 4;
    private static final int REQUESTS_PER_THREAD = 100_000;
    private static final int ROOM_COUNT = 1_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Lab 15: Thread synchronization in hotel booking ===");
        System.out.println("Threads: " + THREAD_COUNT);
        System.out.println("Requests per thread: " + REQUESTS_PER_THREAD);
        System.out.println("Initial rooms: " + ROOM_COUNT);

        runUnsafeScenario();
        runSafeScenario();
        runSequentialScenario();
    }

    private static void runUnsafeScenario() throws InterruptedException {
        UnsafeHotelRoomPool pool = new UnsafeHotelRoomPool(createRooms());
        long start = System.nanoTime();
        runParallelTasks(pool);
        long timeMs = (System.nanoTime() - start) / 1_000_000;

        System.out.println("\n--- Unsafe scenario ---");
        System.out.println("Reservations: " + pool.getReservationCount());
        System.out.println("Available rooms: " + pool.getAvailableRoomCount());
        System.out.println("Expected maximum reservations: " + ROOM_COUNT);
        System.out.println("Time: " + timeMs + " ms");
    }

    private static void runSafeScenario() throws InterruptedException {
        SynchronizedHotelRoomPool pool = new SynchronizedHotelRoomPool(createRooms());
        long start = System.nanoTime();
        runParallelTasks(pool);
        long timeMs = (System.nanoTime() - start) / 1_000_000;

        System.out.println("\n--- Synchronized scenario ---");
        System.out.println("Reservations: " + pool.getReservationCount());
        System.out.println("Available rooms: " + pool.getAvailableRoomCount());
        System.out.println("Expected reservations: " + ROOM_COUNT);
        System.out.println("Time: " + timeMs + " ms");
    }

    private static void runSequentialScenario() {
        SynchronizedHotelRoomPool pool = new SynchronizedHotelRoomPool(createRooms());
        List<BookingRequest> requests = createRequests(THREAD_COUNT * REQUESTS_PER_THREAD, "SEQ");
        long start = System.nanoTime();
        for (BookingRequest request : requests) {
            pool.tryBook(request);
        }
        long timeMs = (System.nanoTime() - start) / 1_000_000;

        System.out.println("\n--- Sequential scenario ---");
        System.out.println("Reservations: " + pool.getReservationCount());
        System.out.println("Available rooms: " + pool.getAvailableRoomCount());
        System.out.println("Time: " + timeMs + " ms");
    }

    private static void runParallelTasks(Object pool) throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            BookingTask task = new BookingTask(createRequests(REQUESTS_PER_THREAD, "T" + i), pool);
            threads[i] = new Thread(task, "BookingWorker-" + (i + 1));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private static List<Room> createRooms() {
        List<Room> rooms = new ArrayList<>();
        for (int i = 1; i <= ROOM_COUNT; i++) {
            rooms.add(new StandardRoom("S-" + i, 80.0, true));
        }
        return rooms;
    }

    private static List<BookingRequest> createRequests(int count, String prefix) {
        List<BookingRequest> requests = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Customer customer = new Customer(prefix + "-Customer-" + i, "guest" + i + "@hotel.test");
            requests.add(new BookingRequest(
                    prefix + "-REQ-" + i,
                    customer,
                    "Standard",
                    LocalDate.of(2026, 6, 1),
                    LocalDate.of(2026, 6, 3)
            ));
        }
        return requests;
    }
}
