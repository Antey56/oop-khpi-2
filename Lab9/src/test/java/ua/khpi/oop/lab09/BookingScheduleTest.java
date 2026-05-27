package ua.khpi.oop.lab09;

import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class BookingScheduleTest {
    @Test(groups = {"domain"})
    public void shouldAddReservationWhenRoomIsFreeForSelectedDates() {
        BookingSchedule<Reservation> schedule = new BookingSchedule<>();
        Reservation reservation = createReservation("R-001", "C-001", LocalDate.of(2026, 6, 10), LocalDate.of(2026, 6, 12));

        schedule.addReservation(reservation);

        assertEquals(schedule.getReservations().size(), 1);
        assertSame(schedule.getReservations().get(0), reservation);
    }

    @Test(groups = {"negative"}, expectedExceptions = IllegalStateException.class)
    public void shouldRejectReservationWhenDatesOverlapForSameRoom() {
        BookingSchedule<Reservation> schedule = new BookingSchedule<>();
        Room room = new StandardRoom("101", 800.0, true);
        Customer firstCustomer = new Customer("C-001", "First Customer");
        Customer secondCustomer = new Customer("C-002", "Second Customer");

        Reservation first = new Reservation(
                "R-001", firstCustomer, room,
                LocalDate.of(2026, 6, 10),
                LocalDate.of(2026, 6, 12)
        );
        Reservation second = new Reservation(
                "R-002", secondCustomer, room,
                LocalDate.of(2026, 6, 11),
                LocalDate.of(2026, 6, 13)
        );

        schedule.addReservation(first);
        schedule.addReservation(second);
    }

    @Test(groups = {"domain"})
    public void shouldAllowSameRoomWhenDatesDoNotOverlap() {
        BookingSchedule<Reservation> schedule = new BookingSchedule<>();
        Room room = new StandardRoom("101", 800.0, true);
        Customer firstCustomer = new Customer("C-001", "First Customer");
        Customer secondCustomer = new Customer("C-002", "Second Customer");

        Reservation first = new Reservation(
                "R-001", firstCustomer, room,
                LocalDate.of(2026, 6, 10),
                LocalDate.of(2026, 6, 12)
        );
        Reservation second = new Reservation(
                "R-002", secondCustomer, room,
                LocalDate.of(2026, 6, 12),
                LocalDate.of(2026, 6, 14)
        );

        schedule.addReservation(first);
        schedule.addReservation(second);

        assertEquals(schedule.getReservations().size(), 2);
    }

    private Reservation createReservation(String reservationId, String customerId, LocalDate from, LocalDate to) {
        Customer customer = new Customer(customerId, "Test Customer");
        Room room = new StandardRoom("101", 800.0, true);
        return new Reservation(reservationId, customer, room, from, to);
    }
}
