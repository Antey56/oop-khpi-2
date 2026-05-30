package ua.khpi.oop.lab11;

import java.util.Objects;

public class Room implements Comparable<Room> {

    private final String number;
    private final String type;
    private final double pricePerNight;

    public Room(String number, String type, double pricePerNight) {
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
    public int compareTo(Room other) {
        return this.number.compareTo(other.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Room room))
            return false;
        return Objects.equals(number, room.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Room{" +
                "number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", pricePerNight=" + pricePerNight +
                '}';
    }
}