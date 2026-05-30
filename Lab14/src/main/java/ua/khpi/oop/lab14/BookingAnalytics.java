package ua.khpi.oop.lab14;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service for Lab 14. It processes hotel reservations using Text Processing and Regular Expressions.
 */
public class BookingAnalytics {
    private final List<Reservation> reservations;

    public BookingAnalytics(List<Reservation> reservations) {
        this.reservations = List.copyOf(reservations);
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }

    public List<Reservation> findActiveReservations() {
        return reservations.stream()
                .filter(Reservation::isActive)
                .collect(Collectors.toList());
    }

    public List<Reservation> findReservationsInDateRange(LocalDate start, LocalDate end) {
        return reservations.stream()
                .filter(reservation -> !reservation.getCheckInDate().isBefore(start))
                .filter(reservation -> !reservation.getCheckOutDate().isAfter(end))
                .sorted(Comparator.comparing(Reservation::getCheckInDate))
                .collect(Collectors.toList());
    }

    public List<String> getCustomerNamesSorted() {
        return reservations.stream()
                .map(reservation -> reservation.getCustomer().getFullName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Reservation> getReservationsSortedByTotalCostDescending() {
        return reservations.stream()
                .sorted(Comparator.comparingDouble(Reservation::totalCost).reversed())
                .collect(Collectors.toList());
    }

    public long countActiveReservations() {
        return reservations.stream()
                .filter(Reservation::isActive)
                .count();
    }

    public double calculateTotalRevenue() {
        return reservations.stream()
                .filter(Reservation::isActive)
                .mapToDouble(Reservation::totalCost)
                .sum();
    }

    public Optional<Reservation> findMostExpensiveReservation() {
        return reservations.stream()
                .max(Comparator.comparingDouble(Reservation::totalCost));
    }

    public Map<String, List<Reservation>> groupReservationsByRoomType() {
        return reservations.stream()
                .collect(Collectors.groupingBy(reservation -> reservation.getRoom().getRoomType()));
    }

    public Map<String, Long> countReservationsByRoomType() {
        return reservations.stream()
                .collect(Collectors.groupingBy(
                        reservation -> reservation.getRoom().getRoomType(),
                        Collectors.counting()));
    }

    public Set<String> getUniqueRoomTypes() {
        return reservations.stream()
                .map(reservation -> reservation.getRoom().getRoomType())
                .collect(Collectors.toSet());
    }

    public DoubleSummaryStatistics getTotalCostStatistics() {
        return reservations.stream()
                .collect(Collectors.summarizingDouble(Reservation::totalCost));
    }

    public List<String> buildShortReservationLines() {
        return reservations.stream()
                .sorted(Comparator.comparing(Reservation::getReservationId))
                .map(reservation -> reservation.getReservationId() + " | "
                        + reservation.getCustomer().getFullName() + " | room "
                        + reservation.getRoom().getRoomNumber() + " | "
                        + reservation.getNights() + " nights | "
                        + String.format("%.2f", reservation.totalCost()))
                .collect(Collectors.toList());
    }

    public String buildRoomTypeSummaryReport() {
        return countReservationsByRoomType().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + ": " + entry.getValue() + " reservations")
                .collect(Collectors.joining("\n", "=== Reservations by room type ===\n", ""));
    }

    public List<Reservation> findByRoomTypeImperative(String roomType) {
        java.util.ArrayList<Reservation> result = new java.util.ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().getRoomType().equalsIgnoreCase(roomType)) {
                result.add(reservation);
            }
        }
        return result;
    }

    public List<Reservation> findByRoomTypeStream(String roomType) {
        return reservations.stream()
                .filter(reservation -> reservation.getRoom().getRoomType().equalsIgnoreCase(roomType))
                .collect(Collectors.toList());
    }
}
