package ua.khpi.oop.lab01;

public class Main {
    public static void main(String[] args) {

        // --- Create guests ---
        Guest guest1 = new Guest("G-01", "Olena Kovalenko", "+380501234567");
        Guest guest2 = new Guest("G-02", "Mykhailo Bondar"); // short constructor

        // --- Create rooms ---
        Room room1 = new Room("101", "single", 800.0); // short constructor
        Room room2 = new Room("205", "double", 1400.0, true); // full constructor

        System.out.println("=== Guests ===");
        System.out.println(guest1);
        System.out.println(guest2);

        System.out.println("\n=== Rooms (before booking) ===");
        System.out.println(room1);
        System.out.println(room2);

        // --- Create bookings ---
        Booking booking1 = new Booking("B-001", guest1, room1, 3);
        Booking booking2 = new Booking("B-002", guest1, room1, 3, false);

        System.out.println("\n=== Bookings ===");
        System.out.println(booking1);
        System.out.println("Total cost: " + booking1.totalCost() + " UAH");

        System.out.println("\n=== Room 101 after booking ===");
        System.out.println(room1);

        // --- equals / hashCode ---
        System.out.println("\n=== equals() and hashCode() for Booking ===");
        System.out.println("booking1.equals(booking2): " + booking1.equals(booking2));
        System.out.println("hashCode booking1: " + booking1.hashCode());
        System.out.println("hashCode booking2: " + booking2.hashCode());

        System.out.println("\n=== equals() and hashCode() for Guest ===");
        Guest guest1copy = new Guest("G-01", "Olena K.", "+380509999999");
        System.out.println("guest1.equals(guest1copy): " + guest1.equals(guest1copy));
        System.out.println("hashCode guest1:     " + guest1.hashCode());
        System.out.println("hashCode guest1copy: " + guest1copy.hashCode());

        // --- Cancel ---
        System.out.println("\n=== After cancelling booking1 ===");
        booking1.cancel();
        System.out.println(booking1);
        System.out.println("Room 101 available: " + room1.isAvailable());

        // --- hasPhone() ---
        System.out.println("\n=== Guest.hasPhone() ===");
        System.out.println(guest1.getFullName() + " has phone: " + guest1.hasPhone());
        System.out.println(guest2.getFullName() + " has phone: " + guest2.hasPhone());
    }
}
