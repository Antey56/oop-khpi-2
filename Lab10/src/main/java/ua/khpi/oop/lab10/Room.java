package ua.khpi.oop.lab10;

import java.util.Objects;

public abstract class Room {
    private final String roomNumber;
    private final String roomType;
    private final double pricePerNight;
    private boolean available;

    protected Room(String roomNumber, String roomType, double pricePerNight) {
        if (roomNumber == null || roomNumber.isBlank()) {
            throw new IllegalArgumentException("Room number must not be blank");
        }
        if (roomType == null || roomType.isBlank()) {
            throw new IllegalArgumentException("Room type must not be blank");
        }
        if (pricePerNight <= 0) {
            throw new IllegalArgumentException("Price per night must be positive");
        }

        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.available = true;
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

    public void book() {
        if (!available) {
            throw new IllegalStateException("Room is already booked");
        }
        available = false;
    }

    public void release() {
        available = true;
    }

    public abstract String getDescription();

    @Override
    public String toString() {
        return "Room{roomNumber='" + roomNumber + "', roomType='" + roomType +
                "', pricePerNight=" + getPricePerNight() + ", available=" + available + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Room other)) {
            return false;
        }
        return Objects.equals(roomNumber, other.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
