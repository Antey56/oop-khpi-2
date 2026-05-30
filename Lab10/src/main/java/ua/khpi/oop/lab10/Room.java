package ua.khpi.oop.lab10;

import java.util.Objects;

public class Room {

    private final String number;
    private final String type;
    private final double pricePerNight;

    public Room(String number, String type, double pricePerNight) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Room number cannot be empty");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Room type cannot be empty");
        }
        if (pricePerNight < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        this.number = number;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    @Override
    public String toString() {
        return "Room{" +
                "number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", pricePerNight=" + pricePerNight +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Room room)) {
            return false;
        }
        return Objects.equals(number, room.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
