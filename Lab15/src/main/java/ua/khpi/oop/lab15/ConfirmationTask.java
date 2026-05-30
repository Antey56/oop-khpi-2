
package ua.khpi.oop.lab15;

import java.util.ArrayList;
import java.util.List;

/**
 * Runnable-based task. It prepares text confirmations for reservations.
 */
public class ConfirmationTask implements Runnable {
    private final List<Reservation> reservations;
    private final BookingThreadService service;
    private final List<String> confirmations = new ArrayList<>();

    public ConfirmationTask(List<Reservation> reservations, BookingThreadService service) {
        this.reservations = List.copyOf(reservations);
        this.service = service;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "] preparing confirmations...");
        for (Reservation reservation : reservations) {
            try {
                Thread.sleep(150);
                if (service.isReservationValid(reservation)) {
                    String confirmation = service.buildConfirmation(reservation);
                    confirmations.add(confirmation);
                    System.out.println("[" + threadName + "] prepared: " + reservation.getReservationId());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("[" + threadName + "] confirmations finished");
    }

    public List<String> getConfirmations() {
        return List.copyOf(confirmations);
    }
}
