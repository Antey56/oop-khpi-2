package ua.khpi.oop.lab14;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service with deterministic hotel-booking logic for Lab 14.
 * It does not depend on thread scheduling, so it can be tested directly.
 */
public class BookingThreadService {
    public boolean isReservationValid(Reservation reservation) {
        return reservation != null
                && reservation.isActive()
                && reservation.getCheckOutDate().isAfter(reservation.getCheckInDate())
                && reservation.totalCost() > 0;
    }

    public String buildConfirmation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation must not be null");
        }
        return "Confirmation for " + reservation.getReservationId()
                + ": " + reservation.getCustomer().getFullName()
                + ", room " + reservation.getRoom().getRoomNumber()
                + ", nights " + reservation.getNights()
                + ", total " + String.format("%.2f", reservation.totalCost());
    }

    public String buildOccupancyReport(List<Reservation> reservations) {
        Map<String, Long> byRoomType = reservations.stream()
                .filter(Reservation::isActive)
                .collect(Collectors.groupingBy(r -> r.getRoom().getRoomType(), Collectors.counting()));

        StringBuilder report = new StringBuilder();
        report.append("Hotel occupancy report\n");
        report.append("======================\n");
        report.append("Active reservations: ")
                .append(reservations.stream().filter(Reservation::isActive).count())
                .append('\n');
        byRoomType.forEach((type, count) -> report.append(type).append(": ").append(count).append('\n'));
        return report.toString();
    }
}
