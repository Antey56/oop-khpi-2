package ua.khpi.oop.lab01;

import java.util.Objects;

public class Room {
    private final String roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean available;

    // Full constructor
    public Room(String roomNumber, String roomType, double pricePerNight, boolean available) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.available = available;
    }

    // Short constructor — available = true by default
    public Room(String roomNumber, String roomType, double pricePerNight) {
        this(roomNumber, roomType, pricePerNight, true);
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void book() {
        this.available = false;
    }

    public void release() {
        this.available = true;
    }

    @Override
    public String toString() {
        return "Room{roomNumber='" + roomNumber + "', roomType='" + roomType +
                "', pricePerNight=" + pricePerNight + ", available=" + available + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Room other))
            return false;
        return Objects.equals(roomNumber, other.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
