package ua.khpi.oop.lab09;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ReservationRecordTest {
    @Test(groups = {"domain"})
    public void shouldStoreReservationWithStringMetadata() {
        Reservation reservation = createReservation();

        ReservationRecord<Reservation, String> record = new ReservationRecord<>(reservation, "confirmed");

        assertSame(record.getReservation(), reservation);
        assertEquals(record.getMetadata(), "confirmed");
    }

    @Test(groups = {"domain"})
    public void shouldStoreReservationWithNumericMetadata() {
        Reservation reservation = createReservation();

        ReservationRecord<Reservation, Double> record = new ReservationRecord<>(reservation, 0.15);

        assertEquals(record.getMetadata(), 0.15);
    }

    private Reservation createReservation() {
        Customer customer = new Customer("C-003", "Maria Bondarenko");
        Room room = new StandardRoom("102", 900.0, false);
        return new Reservation("R-002", customer, room, 1);
    }
}
