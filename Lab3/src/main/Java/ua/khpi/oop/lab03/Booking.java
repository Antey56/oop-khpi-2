package ua.khpi.oop.lab03;

import java.util.Objects;

public class Booking {
    private final String bookingId;
    private Guest guest;
    private Room room;
    private int nights;
    private boolean active;

    public Booking(String bookingId, Guest guest, Room room, int nights, boolean active) {
        if (bookingId == null || bookingId.isBlank()) {
            throw new IllegalArgumentException("Booking id must not be blank");
        }
        if (guest == null) {
            throw new IllegalArgumentException("Guest must not be null");
        }
        if (room == null) {
            throw new IllegalArgumentException("Room must not be null");
        }
        if (nights <= 0) {
            throw new IllegalArgumentException("Nights must be positive");
        }

        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.nights = nights;
        this.active = active;
    }

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
        if (nights <= 0) {
            throw new IllegalArgumentException("Nights must be positive");
        }
        this.nights = nights;
    }

    public double totalCost() {
        return room.getPricePerNight() * nights;
    }

    public void cancel() {
        active = false;
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