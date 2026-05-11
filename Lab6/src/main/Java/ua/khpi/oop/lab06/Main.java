package ua.khpi.oop.lab06;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Kharkiv Palace", 5);

        Room standardRoom = new StandardRoom("101", 800.0, true);
        Room familyRoom = new FamilyRoom("205", 1200.0, 4);
        Room luxuryRoom = new LuxuryRoom("310", 2000.0, true);

        hotel.addRoom(standardRoom);
        hotel.addRoom(familyRoom);
        hotel.addRoom(luxuryRoom);

        Guest guest = new Guest("G-01", "Olena Kovalenko", "+380501234567");

        System.out.println("=== Hotel ===");
        System.out.println(hotel);

        System.out.println("\n=== Room descriptions ===");
        hotel.printRoomDescriptions();

        Booking booking = new Booking("B-001", guest, luxuryRoom, 2);

        System.out.println("\n=== Booking ===");
        System.out.println(booking);
        System.out.println("Total cost: " + booking.totalCost());

        System.out.println("\n=== Polymorphism demonstration ===");

        Room[] rooms = {
                standardRoom,
                familyRoom,
                luxuryRoom
        };

        for (Room room : rooms) {
            System.out.println(room.getDescription());
            System.out.println("Type: " + room.getRoomType());
            System.out.println("Price: " + room.getPricePerNight());
            System.out.println("------------------------");
        }
    }
}