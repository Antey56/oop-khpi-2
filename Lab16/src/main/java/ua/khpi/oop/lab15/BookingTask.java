package ua.khpi.oop.lab15;

import java.util.List;

public class BookingTask implements Runnable {
    private final List<BookingRequest> requests;
    private final Object roomPool;
    private int successfulBookings;

    public BookingTask(List<BookingRequest> requests, Object roomPool) {
        this.requests = requests;
        this.roomPool = roomPool;
    }

    @Override
    public void run() {
        for (BookingRequest request : requests) {
            boolean booked;
            if (roomPool instanceof SynchronizedHotelRoomPool safePool) {
                booked = safePool.tryBook(request);
            } else if (roomPool instanceof UnsafeHotelRoomPool unsafePool) {
                booked = unsafePool.tryBook(request);
            } else {
                throw new IllegalStateException("Unsupported room pool type");
            }
            if (booked) {
                successfulBookings++;
            }
        }
    }

    public int getSuccessfulBookings() {
        return successfulBookings;
    }
}
