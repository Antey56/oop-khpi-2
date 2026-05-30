package ua.khpi.oop.lab12;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Registry from Lab 11. In Lab 12 it remains the source of prepared collection data
 * for Stream API processing.
 */
public class HotelBookingRegistry {
    private final List<Reservation> reservationsInOrder = new ArrayList<>();
    private final Map<String, Reservation> reservationsById = new HashMap<>();
    private final Set<Customer> uniqueCustomers = new HashSet<>();
    private final Queue<Reservation> processingQueue = new ArrayDeque<>();

    public void addReservation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation must not be null");
        }
        if (reservationsById.containsKey(reservation.getReservationId())) {
            throw new IllegalArgumentException("Reservation with id " + reservation.getReservationId() + " already exists");
        }
        reservationsInOrder.add(reservation);
        reservationsById.put(reservation.getReservationId(), reservation);
        uniqueCustomers.add(reservation.getCustomer());
        processingQueue.offer(reservation);
    }

    public Reservation findById(String reservationId) {
        return reservationsById.get(reservationId);
    }

    public Reservation removeReservation(String reservationId) {
        Reservation removed = reservationsById.remove(reservationId);
        if (removed != null) {
            reservationsInOrder.remove(removed);
            processingQueue.remove(removed);
            rebuildUniqueCustomers();
        }
        return removed;
    }

    public boolean cancelReservation(String reservationId) {
        Reservation reservation = reservationsById.get(reservationId);
        if (reservation == null) {
            return false;
        }
        reservation.cancel();
        return true;
    }

    public List<Reservation> getAllReservations() {
        return Collections.unmodifiableList(reservationsInOrder);
    }

    public Set<Customer> getUniqueCustomers() {
        return Collections.unmodifiableSet(uniqueCustomers);
    }

    public Map<String, Reservation> getReservationsByIdSnapshot() {
        return new HashMap<>(reservationsById);
    }

    public int size() {
        return reservationsInOrder.size();
    }

    public boolean isEmpty() {
        return reservationsInOrder.isEmpty();
    }

    private void rebuildUniqueCustomers() {
        uniqueCustomers.clear();
        for (Reservation reservation : reservationsInOrder) {
            uniqueCustomers.add(reservation.getCustomer());
        }
    }
}
