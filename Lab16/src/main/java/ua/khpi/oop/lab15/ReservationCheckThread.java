
package ua.khpi.oop.lab15;

import java.util.ArrayList;
import java.util.List;

/**
 * Thread-based task. It checks reservation requests step by step.
 */
public class ReservationCheckThread extends Thread {
    private final List<Reservation> reservations;
    private final BookingThreadService service;
    private final List<Reservation> validReservations = new ArrayList<>();

    public ReservationCheckThread(String name, List<Reservation> reservations, BookingThreadService service) {
        super(name);
        this.reservations = List.copyOf(reservations);
        this.service = service;
    }

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] checking reservation requests...");
        for (Reservation reservation : reservations) {
            try {
                Thread.sleep(120);
                if (service.isReservationValid(reservation)) {
                    validReservations.add(reservation);
                    System.out.println("[" + Thread.currentThread().getName() + "] valid: " + reservation.getReservationId());
                } else {
                    System.out.println("[" + Thread.currentThread().getName() + "] skipped: " + reservation.getReservationId());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("[" + Thread.currentThread().getName() + "] checking finished");
    }

    public List<Reservation> getValidReservations() {
        return List.copyOf(validReservations);
    }
}
