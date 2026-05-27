package ua.khpi.oop.lab08;

import java.io.IOException;

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

        System.out.println("\n=== Interface demonstration ===");

        Reservable reservable = new LuxuryRoom("401", 2500.0, true);
        reservable.reserve();

        Cleanable cleanable = new LuxuryRoom("402", 2600.0, true);
        cleanable.clean();

        Bookable bookable = new FamilyRoom("501", 1500.0, 5);
        bookable.book();
        try {
            HotelFileManager.saveHotel(hotel, "hotel.dat");
            Hotel loadedHotel = HotelFileManager.loadHotel("hotel.dat");

            System.out.println("\n=== Loaded hotel from file ===");
            System.out.println(loadedHotel);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File operation error: " + e.getMessage());
        }
    }
}
