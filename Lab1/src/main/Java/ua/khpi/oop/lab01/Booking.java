package ua.khpi.oop.lab01;

import java.util.Objects;

public class Booking {
    private final String bookingId;
    private Guest guest;
    private Room room;
    private int nights;
    private boolean active;

    // Full constructor
    public Booking(String bookingId, Guest guest, Room room, int nights, boolean active) {
        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.nights = nights;
        this.active = active;
    }

    // Short constructor — active = true, automatically books the room
    public Booking(String bookingId, Guest guest, Room room, int nights) {
        this(bookingId, guest, room, nights, true);
        room.book();
    }

    public String getBookingId() {
        return bookingId;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public int getNights() {
        return nights;
    }

    public boolean isActive() {
        return active;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public double totalCost() {
        return room.getPricePerNight() * nights;
    }

    public void cancel() {
        this.active = false;
        room.release();
    }

    @Override
    public String toString() {
        return "Booking{bookingId='" + bookingId + "', guest=" + guest.getFullName() +
                ", room=" + room.getRoomNumber() + ", nights=" + nights +
                ", totalCost=" + totalCost() + ", active=" + active + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Booking other))
            return false;
        return Objects.equals(bookingId, other.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }
}
