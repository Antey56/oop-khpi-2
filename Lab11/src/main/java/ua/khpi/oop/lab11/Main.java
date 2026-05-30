
package ua.khpi.oop.lab11;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        HotelBookingRegistry registry = new HotelBookingRegistry();

        Customer customer = new Customer("C-001", "Olena Kovalenko", "+380501234567");
        VipCustomer vipCustomer = new VipCustomer("VIP-001", "Danylo Vintoniak", "+4915112345678", 3);

        StandardRoom standardRoom = new StandardRoom("101", 800.0, true);
        FamilyRoom familyRoom = new FamilyRoom("205", 1200.0, 4);
        LuxuryRoom luxuryRoom = new LuxuryRoom("310", 2000.0, true);

        Reservation r1 = new Reservation("R-001", customer, standardRoom,
                LocalDate.of(2026, 6, 10), LocalDate.of(2026, 6, 12));
        Reservation r2 = new Reservation("R-002", vipCustomer, luxuryRoom,
                LocalDate.of(2026, 6, 12), LocalDate.of(2026, 6, 15));
        Reservation r3 = new Reservation("R-003", customer, familyRoom,
                LocalDate.of(2026, 6, 18), LocalDate.of(2026, 6, 20));

        registry.addReservation(r1);
        registry.addReservation(r2);
        registry.addReservation(r3);

        System.out.println("=== Lab 11: Collections Framework in hotel booking system ===");
        System.out.println("Total reservations: " + registry.size());
        System.out.println("Unique customers: " + registry.getUniqueCustomers().size());

        System.out.println("
=== List: reservations in order ===");
        for (Reservation reservation : registry.getAllReservations()) {
            System.out.println(reservation);
        }

        System.out.println("
=== Map: fast search by reservation id ===");
        System.out.println(registry.findById("R-002"));

        System.out.println("
=== Map entrySet traversal ===");
        for (Map.Entry<String, Reservation> entry : registry.getReservationsByIdSnapshot().entrySet()) {
            System.out.println(entry.getKey() + " -> room " + entry.getValue().getRoom().getRoomNumber());
        }

        System.out.println("
=== Iterator traversal ===");
        Iterator<Reservation> iterator = registry.getAllReservations().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getReservationId());
        }

        System.out.println("
=== Queue: processing reservations ===");
        Queue<Reservation> queue = registry.getProcessingQueueSnapshot();
        while (!queue.isEmpty()) {
            System.out.println("Processing " + queue.poll().getReservationId());
        }

        System.out.println("
=== Sorted by total cost ===");
        for (Reservation reservation : registry.getReservationsSortedByTotalCostDescending()) {
            System.out.println(reservation.getReservationId() + " -> " + reservation.totalCost());
        }

        System.out.println("
=== Text report ===");
        System.out.println(registry.buildReservationReport());
    }
}
