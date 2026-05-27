package ua.khpi.oop.lab09;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingSchedule<TReservation extends Reservation> {
    private final List<TReservation> reservations = new ArrayList<>();

    public void addReservation(TReservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation must not be null");
        }
        for (TReservation existing : reservations) {
            if (existing.overlapsWith(reservation)) {
                throw new IllegalStateException("Room " + reservation.getRoom().getRoomNumber()
                        + " is already booked for selected dates");
            }
        }
        reservations.add(reservation);
    }

    public boolean isRoomAvailable(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        Reservation candidate = new Reservation("TEMP", new Customer("TEMP", "Temporary customer"), room, checkInDate, checkOutDate);
        for (TReservation existing : reservations) {
            if (existing.overlapsWith(candidate)) {
                return false;
            }
        }
        return true;
    }

    public List<TReservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }
}
