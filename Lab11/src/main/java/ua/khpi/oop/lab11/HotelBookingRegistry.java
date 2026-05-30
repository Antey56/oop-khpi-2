
package ua.khpi.oop.lab11;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Service/registry for Lab 11. It uses standard Java Collections Framework
 * instead of the custom container from Lab 10.
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

    public boolean containsReservation(String reservationId) {
        return reservationsById.containsKey(reservationId);
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

    public Reservation pollNextForProcessing() {
        return processingQueue.poll();
    }

    public List<Reservation> getAllReservations() {
        return Collections.unmodifiableList(reservationsInOrder);
    }

    public Set<Customer> getUniqueCustomers() {
        return Collections.unmodifiableSet(uniqueCustomers);
    }

    public Queue<Reservation> getProcessingQueueSnapshot() {
        return new ArrayDeque<>(processingQueue);
    }

    public Map<String, Reservation> getReservationsByIdSnapshot() {
        return new HashMap<>(reservationsById);
    }

    public List<Reservation> findByCustomer(Customer customer) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation reservation : reservationsInOrder) {
            if (reservation.getCustomer().equals(customer)) {
                result.add(reservation);
            }
        }
        return result;
    }

    public List<Reservation> findByRoomType(String roomType) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation reservation : reservationsInOrder) {
            if (reservation.getRoom().getRoomType().equalsIgnoreCase(roomType)) {
                result.add(reservation);
            }
        }
        return result;
    }

    public List<Reservation> getReservationsSortedByTotalCostDescending() {
        List<Reservation> sorted = new ArrayList<>(reservationsInOrder);
        sorted.sort(Comparator.comparingDouble(Reservation::totalCost).reversed());
        return sorted;
    }

    public String buildReservationReport() {
        StringBuilder report = new StringBuilder("=== Reservations by ID ===
");
        for (Map.Entry<String, Reservation> entry : reservationsById.entrySet()) {
            report.append(entry.getKey()).append(" -> ")
                    .append(entry.getValue().getCustomer().getFullName())
                    .append(", room ")
                    .append(entry.getValue().getRoom().getRoomNumber())
                    .append('
');
        }
        return report.toString();
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
