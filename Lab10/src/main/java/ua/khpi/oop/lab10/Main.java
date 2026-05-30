package ua.khpi.oop.lab10;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("C-001", "Olena Kovalenko", "+380501234567");
        VipCustomer vipCustomer = new VipCustomer("VIP-001", "Danylo Vintoniak", "+4915112345678", 3);

        StandardRoom standardRoom = new StandardRoom("101", 800.0, true);
        FamilyRoom familyRoom = new FamilyRoom("205", 1200.0, 4);
        LuxuryRoom luxuryRoom = new LuxuryRoom("310", 2000.0, true);

        BookingLink<Customer, StandardRoom> standardLink = new BookingLink<>(customer, standardRoom);
        BookingLink<VipCustomer, LuxuryRoom> vipLink = new BookingLink<>(vipCustomer, luxuryRoom);

        System.out.println("=== Generic booking links ===");
        System.out.println(standardLink);
        System.out.println(vipLink);

        BookingSchedule<Reservation> schedule = new BookingSchedule<>();
        Reservation firstReservation = standardLink.createReservation(
                "R-001",
                LocalDate.of(2026, 6, 10),
                LocalDate.of(2026, 6, 12)
        );
        Reservation secondReservation = vipLink.createReservation(
                "R-002",
                LocalDate.of(2026, 6, 12),
                LocalDate.of(2026, 6, 14)
        );
        schedule.addReservation(firstReservation);
        schedule.addReservation(secondReservation);

        ReservationRecord<Reservation, String> statusRecord = new ReservationRecord<>(firstReservation, "confirmed");
        ReservationRecord<Reservation, Double> discountRecord = new ReservationRecord<>(secondReservation, 0.10);

        ReservationContainer<ReservationRecord<? extends Reservation, ?>> records = new ReservationContainer<>();
        records.add(statusRecord);
        records.add(discountRecord);

        System.out.println("\n=== Custom generic container: direct access ===");
        System.out.println("Container size: " + records.size());
        System.out.println("First record: " + records.get(0));

        System.out.println("\n=== Traversal with explicit iterator ===");
        java.util.Iterator<ReservationRecord<? extends Reservation, ?>> iterator = records.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("\n=== Traversal with for-each ===");
        for (ReservationRecord<? extends Reservation, ?> record : records) {
            System.out.println(record);
        }

        System.out.println("\n=== Removing one record ===");
        ReservationRecord<? extends Reservation, ?> removed = records.remove(0);
        System.out.println("Removed: " + removed);
        System.out.println("Container size after remove: " + records.size());

        List<Room> rooms = List.of(standardRoom, familyRoom, luxuryRoom);
        Room mostExpensiveRoom = BookingUtils.findMostExpensiveRoom(rooms);
        Room availableRoom = BookingUtils.findFirstAvailableRoom(
                rooms,
                schedule,
                LocalDate.of(2026, 6, 10),
                LocalDate.of(2026, 6, 12)
        );

        System.out.println("\n=== Previous generic methods still work ===");
        System.out.println("Most expensive room: " + mostExpensiveRoom);
        System.out.println("First available room for selected dates: " + availableRoom);
    }
}
