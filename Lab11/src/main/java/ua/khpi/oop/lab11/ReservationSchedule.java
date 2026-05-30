package ua.khpi.oop.lab10;

public class ReservationSchedule<T extends Reservation> implements Iterable<T> {

    private final ReservationContainer<T> reservations = new ReservationContainer<>();

    public boolean addReservation(T reservation) {
        if (hasConflict(reservation)) {
            return false;
        }

        reservations.add(reservation);
        return true;
    }

    public boolean hasConflict(T reservation) {
        for (T existingReservation : reservations) {
            if (existingReservation.overlaps(reservation)) {
                return true;
            }
        }

        return false;
    }

    public T get(int index) {
        return reservations.get(index);
    }

    public T remove(int index) {
        return reservations.remove(index);
    }

    public int size() {
        return reservations.size();
    }

    public boolean isEmpty() {
        return reservations.isEmpty();
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return reservations.iterator();
    }
}
