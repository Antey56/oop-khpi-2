package ua.khpi.oop.lab10;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

public class BookingUtilsTest {
    @Test(groups = {"smoke", "domain"})
    public void shouldFindMostExpensiveRoom() {
        StandardRoom standardRoom = new StandardRoom("101", 800.0, true);
        FamilyRoom familyRoom = new FamilyRoom("205", 1200.0, 4);
        LuxuryRoom luxuryRoom = new LuxuryRoom("310", 2000.0, true);

        Room result = BookingUtils.findMostExpensiveRoom(List.of(standardRoom, familyRoom, luxuryRoom));

        assertSame(result, luxuryRoom);
        assertEquals(result.getPricePerNight(), 3000.0);
    }

    @Test(groups = {"domain"})
    public void shouldFindFirstAvailableRoom() {
        StandardRoom first = new StandardRoom("101", 800.0, true);
        FamilyRoom second = new FamilyRoom("205", 1200.0, 4);
        first.book();

        Room result = BookingUtils.findFirstAvailableRoom(List.of(first, second));

        assertSame(result, second);
    }

    @Test(groups = {"domain"})
    public void shouldFindFirstAvailableRoomForSelectedDates() {
        StandardRoom first = new StandardRoom("101", 800.0, true);
        StandardRoom second = new StandardRoom("102", 900.0, false);
        Customer customer = new Customer("C-001", "Test Customer");
        BookingSchedule<Reservation> schedule = new BookingSchedule<>();
        schedule.addReservation(new Reservation(
                "R-001",
                customer,
                first,
                LocalDate.of(2026, 6, 10),
                LocalDate.of(2026, 6, 12)
        ));

        StandardRoom result = BookingUtils.findFirstAvailableRoom(
                List.of(first, second),
                schedule,
                LocalDate.of(2026, 6, 11),
                LocalDate.of(2026, 6, 13)
        );

        assertSame(result, second);
    }

    @Test(groups = {"domain"})
    public void shouldReturnNullForEmptyList() {
        assertNull(BookingUtils.findMostExpensiveRoom(Collections.emptyList()));
        assertNull(BookingUtils.findFirstAvailableRoom(Collections.emptyList()));
    }

    @Test(groups = {"domain"})
    public void shouldPreserveConcreteReturnType() {
        LuxuryRoom first = new LuxuryRoom("310", 2000.0, true);
        LuxuryRoom second = new LuxuryRoom("311", 2500.0, false);

        LuxuryRoom result = BookingUtils.findMostExpensiveRoom(List.of(first, second));

        assertSame(result, second);
    }
}
