package ua.khpi.oop.lab10;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {

    private final String id;
    private final Customer customer;
    private final Room room;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;

    public Reservation(String id, Customer customer, Room room,
                       LocalDate checkInDate, LocalDate checkOutDate) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Reservation id cannot be empty");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        if (!checkOutDate.isAfter(checkInDate)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }

        this.id = id;
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public boolean overlaps(Reservation other) {
        if (other == null) {
            return false;
        }

        return room.equals(other.room)
                && checkInDate.isBefore(other.checkOutDate)
                && checkOutDate.isAfter(other.checkInDate);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Reservation that)) {
            return false;
        }
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
