package ua.khpi.oop.lab10;

import java.time.LocalDate;

public class BookingSchedule<TReservation extends Reservation> {
    private final ReservationContainer<TReservation> reservations = new ReservationContainer<>();

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

    public ReservationContainer<TReservation> getReservations() {
        return reservations;
    }
}
