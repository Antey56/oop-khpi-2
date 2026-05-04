package ua.khpi.oop.lab01;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Kharkiv Palace", 5);

        Room room1 = new Room("101", "single", 800.0);
        Room room2 = new Room("205", "double", 1400.0, true);
        Room room3 = new Room("310", "suite", 2500.0);

        hotel.addRoom(room1);
        hotel.addRoom(room2);
        hotel.addRoom(room3);

        Guest guest1 = new Guest("G-01", "Olena Kovalenko", "+380501234567");
        Guest guest2 = new Guest("G-02", "Mykhailo Bondar");

        System.out.println("=== Hotel ===");
        System.out.println(hotel);

        System.out.println("\n=== Rooms before booking ===");
        hotel.printRooms();

        Booking booking1 = new Booking("B-001", guest1, room1, 3);

        System.out.println("\n=== First booking ===");
        System.out.println(booking1);
        System.out.println("Total cost: " + booking1.totalCost() + " UAH");

        System.out.println("\n=== Rooms after first booking ===");
        hotel.printRooms();

        Room availableRoom = hotel.findAvailableRoom();

        System.out.println("\n=== First available room ===");
        if (availableRoom != null) {
            System.out.println(availableRoom);
        } else {
            System.out.println("No available rooms");
        }

        Booking booking2 = new Booking("B-002", guest2, room2, 2);

        System.out.println("\n=== Second booking ===");
        System.out.println(booking2);

        System.out.println("\n=== Rooms after second booking ===");
        hotel.printRooms();

        System.out.println("\n=== equals() and hashCode() ===");
        Room room1Copy = new Room("101", "single", 800.0);
        System.out.println("room1.equals(room1Copy): " + room1.equals(room1Copy));
        System.out.println("hashCode room1: " + room1.hashCode());
        System.out.println("hashCode room1Copy: " + room1Copy.hashCode());

        System.out.println("\n=== After cancelling first booking ===");
        booking1.cancel();
        System.out.println(booking1);

        System.out.println("\n=== Rooms after cancellation ===");
        hotel.printRooms();
    }
}