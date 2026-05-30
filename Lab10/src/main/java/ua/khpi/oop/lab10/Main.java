package ua.khpi.oop.lab10;

import java.time.LocalDate;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        ReservationContainer<Reservation> reservations = new ReservationContainer<>(2);

        Room standardRoom = new Room("101", "Standard", 1200.0);
        Room luxuryRoom = new Room("202", "Luxury", 2500.0);

        Customer customer1 = new Customer("Danylo", "+380111111");
        Customer customer2 = new Customer("Max", "+380222222");

        Reservation reservation1 = new Reservation(
                "R001",
                customer1,
                standardRoom,
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 5, 25)
        );

        Reservation reservation2 = new Reservation(
                "R002",
                customer2,
                luxuryRoom,
                LocalDate.of(2026, 6, 1),
                LocalDate.of(2026, 6, 5)
        );

        reservations.add(reservation1);
        reservations.add(reservation2);

        System.out.println("=== Access by index ===");
        System.out.println(reservations.get(0));

        System.out.println("\n=== Iterator traversal ===");
        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("\n=== For-each traversal ===");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }

        System.out.println("\n=== Remove first reservation ===");
        Reservation removed = reservations.remove(0);
        System.out.println("Removed: " + removed);

        System.out.println("\n=== After removal ===");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }

        System.out.println("\n=== Schedule conflict demo ===");
        ReservationSchedule<Reservation> schedule = new ReservationSchedule<>();
        System.out.println("First reservation added: " + schedule.addReservation(reservation1));

        Reservation conflictingReservation = new Reservation(
                "R003",
                customer2,
                standardRoom,
                LocalDate.of(2026, 5, 22),
                LocalDate.of(2026, 5, 24)
        );

        System.out.println("Conflicting reservation added: " + schedule.addReservation(conflictingReservation));
    }
}
