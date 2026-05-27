package ua.khpi.oop.lab09;

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
        Reservation reservation = vipLink.createReservation(
                "R-001",
                LocalDate.of(2026, 6, 10),
                LocalDate.of(2026, 6, 12)
        );
        schedule.addReservation(reservation);

        ReservationRecord<Reservation, String> recordWithStatus = new ReservationRecord<>(reservation, "confirmed");
        ReservationRecord<Reservation, Double> recordWithDiscount = new ReservationRecord<>(reservation, 0.10);

        System.out.println("\n=== Generic reservation records ===");
        System.out.println(recordWithStatus);
        System.out.println(recordWithDiscount);

        List<Room> rooms = List.of(standardRoom, familyRoom, luxuryRoom);
        Room mostExpensiveRoom = BookingUtils.findMostExpensiveRoom(rooms);
        Room availableRoom = BookingUtils.findFirstAvailableRoom(
                rooms,
                schedule,
                LocalDate.of(2026, 6, 10),
                LocalDate.of(2026, 6, 12)
        );

        System.out.println("\n=== Generic methods ===");
        System.out.println("Most expensive room: " + mostExpensiveRoom);
        System.out.println("First available room for selected dates: " + availableRoom);
    }
}
