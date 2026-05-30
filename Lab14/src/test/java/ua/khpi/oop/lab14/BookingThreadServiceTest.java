
package ua.khpi.oop.lab14;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingThreadServiceTest {
    private final BookingThreadService service = new BookingThreadService();

    @Test
    void validatesOnlyActiveReservation() {
        Reservation reservation = sampleReservation("R-001");
        assertTrue(service.isReservationValid(reservation));

        reservation.cancel();
        assertFalse(service.isReservationValid(reservation));
    }

    @Test
    void confirmationContainsMainReservationFields() {
        Reservation reservation = sampleReservation("R-002");
        String confirmation = service.buildConfirmation(reservation);

        assertTrue(confirmation.contains("R-002"));
        assertTrue(confirmation.contains("John Test"));
        assertTrue(confirmation.contains("101"));
    }

    @Test
    void reportCountsOnlyActiveReservations() {
        Reservation active = sampleReservation("R-003");
        Reservation cancelled = sampleReservation("R-004");
        cancelled.cancel();

        String report = service.buildOccupancyReport(List.of(active, cancelled));

        assertTrue(report.contains("Active reservations: 1"));
        assertTrue(report.contains("standard: 1"));
    }

    @Test
    void confirmationTaskCanBeTestedByDirectRunCall() {
        ConfirmationTask task = new ConfirmationTask(List.of(sampleReservation("R-005")), service);
        task.run();
        assertEquals(1, task.getConfirmations().size());
    }

    private Reservation sampleReservation(String id) {
        Customer customer = new Customer("C-777", "John Test", "+491234567890");
        Room room = new StandardRoom("101", 100.0, true);
        return new Reservation(id, customer, room,
                LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 4));
    }
}
