package ua.khpi.oop.lab15;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Reservation {
    private final String reservationId;
    private final Customer customer;
    private final Room room;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private boolean active;

    public Reservation(String reservationId, Customer customer, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        if (reservationId == null || reservationId.isBlank()) {
            throw new IllegalArgumentException("Reservation id must not be blank");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer must not be null");
        }
        if (room == null) {
            throw new IllegalArgumentException("Room must not be null");
        }
        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalArgumentException("Reservation dates must not be null");
        }
        if (!checkOutDate.isAfter(checkInDate)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }
        this.reservationId = reservationId;
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.active = true;
    }

    public Reservation(String reservationId, Customer customer, Room room, int nights) {
        this(reservationId, customer, room, LocalDate.now(), LocalDate.now().plusDays(nights));
    }

    public String getReservationId() {
        return reservationId;
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

    public int getNights() {
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    public boolean isActive() {
        return active;
    }

    public boolean overlapsWith(Reservation other) {
        if (other == null || !active || !other.active) {
            return false;
        }
        return room.equals(other.room)
                && checkInDate.isBefore(other.checkOutDate)
                && other.checkInDate.isBefore(checkOutDate);
    }

    public double totalCost() {
        return room.getPricePerNight() * getNights();
    }

    public void cancel() {
        active = false;
    }

    @Override
    public String toString() {
        return "Reservation{reservationId='" + reservationId + "', customer=" + customer.getFullName() +
                ", room=" + room.getRoomNumber() + ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate + ", nights=" + getNights() +
                ", totalCost=" + totalCost() + ", active=" + active + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Reservation other)) {
            return false;
        }
        return Objects.equals(reservationId, other.reservationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId);
    }
}
