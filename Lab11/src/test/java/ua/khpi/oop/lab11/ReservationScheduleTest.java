package ua.khpi.oop.lab10;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservationScheduleTest {

    @Test
    void testAddReservationWithoutConflict() {
        ReservationSchedule<Reservation> schedule = new ReservationSchedule<>();

        Room room = new Room("101", "Standard", 1200.0);
        Customer customer = new Customer("Danylo", "+380111111");

        Reservation reservation = new Reservation(
                "R001",
                customer,
                room,
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 5, 25)
        );

        assertTrue(schedule.addReservation(reservation));
        assertEquals(1, schedule.size());
    }

    @Test
    void testRejectOverlappingReservationForSameRoom() {
        ReservationSchedule<Reservation> schedule = new ReservationSchedule<>();

        Room room = new Room("101", "Standard", 1200.0);

        Customer customer1 = new Customer("Danylo", "+380111111");
        Customer customer2 = new Customer("Max", "+380222222");

        Reservation firstReservation = new Reservation(
                "R001",
                customer1,
                room,
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 5, 25)
        );

        Reservation secondReservation = new Reservation(
                "R002",
                customer2,
                room,
                LocalDate.of(2026, 5, 22),
                LocalDate.of(2026, 5, 24)
        );

        assertTrue(schedule.addReservation(firstReservation));
        assertFalse(schedule.addReservation(secondReservation));
        assertEquals(1, schedule.size());
    }

    @Test
    void testAllowSameRoomForDifferentDates() {
        ReservationSchedule<Reservation> schedule = new ReservationSchedule<>();

        Room room = new Room("101", "Standard", 1200.0);

        Customer customer1 = new Customer("Danylo", "+380111111");
        Customer customer2 = new Customer("Max", "+380222222");

        Reservation firstReservation = new Reservation(
                "R001",
                customer1,
                room,
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 5, 25)
        );

        Reservation secondReservation = new Reservation(
                "R002",
                customer2,
                room,
                LocalDate.of(2026, 5, 25),
                LocalDate.of(2026, 5, 30)
        );

        assertTrue(schedule.addReservation(firstReservation));
        assertTrue(schedule.addReservation(secondReservation));
        assertEquals(2, schedule.size());
    }
}
