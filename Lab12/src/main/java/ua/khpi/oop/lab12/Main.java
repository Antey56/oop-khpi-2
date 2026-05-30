package ua.khpi.oop.lab12;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Customer customer1 = new Customer("C-001", "Ivan Petrenko", "+380501111111");
        Customer customer2 = new VipCustomer("C-002", "Olena Shevchenko", "+380502222222", 3);
        Customer customer3 = new Customer("C-003", "Mark Johnson", "+491701234567");

        Room standard101 = new StandardRoom("101", 80.0, true);
        Room family202 = new FamilyRoom("202", 120.0, 4);
        Room luxury501 = new LuxuryRoom("501", 200.0, true);

        List<Reservation> reservations = Arrays.asList(
                new Reservation("R-001", customer1, standard101, LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 4)),
                new Reservation("R-002", customer2, luxury501, LocalDate.of(2026, 6, 3), LocalDate.of(2026, 6, 8)),
                new Reservation("R-003", customer3, family202, LocalDate.of(2026, 6, 10), LocalDate.of(2026, 6, 14)),
                new Reservation("R-004", customer1, luxury501, LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 3)),
                new Reservation("R-005", customer2, standard101, LocalDate.of(2026, 7, 5), LocalDate.of(2026, 7, 8))
        );
        reservations.get(4).cancel();

        HotelBookingRegistry registry = new HotelBookingRegistry();
        reservations.forEach(registry::addReservation);

        BookingAnalytics analytics = new BookingAnalytics(registry.getAllReservations());

        System.out.println("=== Lab 12: Stream API for Hotel Booking System ===\n");

        System.out.println("Active reservations:");
        analytics.findActiveReservations().forEach(System.out::println);

        System.out.println("\nCustomer names sorted:");
        analytics.getCustomerNamesSorted().forEach(name -> System.out.println("- " + name));

        System.out.println("\nReservations sorted by total cost:");
        analytics.getReservationsSortedByTotalCostDescending()
                .forEach(reservation -> System.out.println(reservation.getReservationId() + " -> " + reservation.totalCost()));

        System.out.println("\nCount by room type:");
        Map<String, Long> counts = analytics.countReservationsByRoomType();
        counts.forEach((type, count) -> System.out.println(type + ": " + count));

        System.out.println("\nDate range 2026-06-01..2026-06-30:");
        analytics.findReservationsInDateRange(LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 30))
                .forEach(reservation -> System.out.println("- " + reservation.getReservationId()));

        System.out.println("\nTotal active revenue: " + analytics.calculateTotalRevenue());

        analytics.findMostExpensiveReservation().ifPresent(reservation ->
                System.out.println("Most expensive reservation: " + reservation.getReservationId()));

        System.out.println("\n" + analytics.buildRoomTypeSummaryReport());

        System.out.println("\nImperative vs Stream, luxury rooms:");
        System.out.println("Imperative: " + analytics.findByRoomTypeImperative("luxury").size());
        System.out.println("Stream: " + analytics.findByRoomTypeStream("luxury").size());
    }
}
