package ua.khpi.oop.lab11;

public class ReservationRecord<TReservation extends Reservation, TMeta> {
    private final TReservation reservation;
    private final TMeta metadata;

    public ReservationRecord(TReservation reservation, TMeta metadata) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation must not be null");
        }
        this.reservation = reservation;
        this.metadata = metadata;
    }

    public TReservation getReservation() {
        return reservation;
    }

    public TMeta getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "ReservationRecord{reservation=" + reservation.getReservationId() +
                ", metadata=" + metadata + '}';
    }
}
