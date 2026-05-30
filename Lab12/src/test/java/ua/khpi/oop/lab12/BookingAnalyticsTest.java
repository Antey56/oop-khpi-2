package ua.khpi.oop.lab12;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class BookingAnalyticsTest {
    private BookingAnalytics analytics;
    private Reservation r1;
    private Reservation r2;
    private Reservation r3;
    private Reservation r4;

    @BeforeMethod
    public void setUp() {
        Customer customer1 = new Customer("C-001", "Ivan Petrenko", "+380501111111");
        Customer customer2 = new VipCustomer("C-002", "Olena Shevchenko", "+380502222222", 2);
        Customer customer3 = new Customer("C-003", "Mark Johnson", "+491701234567");

        Room standard = new StandardRoom("101", 80.0, true);
        Room family = new FamilyRoom("202", 120.0, 4);
        Room luxury = new LuxuryRoom("501", 200.0, true);

        r1 = new Reservation("R-001", customer1, standard, LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 4));
        r2 = new Reservation("R-002", customer2, luxury, LocalDate.of(2026, 6, 3), LocalDate.of(2026, 6, 8));
        r3 = new Reservation("R-003", customer3, family, LocalDate.of(2026, 6, 10), LocalDate.of(2026, 6, 14));
        r4 = new Reservation("R-004", customer1, luxury, LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 3));
        r4.cancel();

        analytics = new BookingAnalytics(List.of(r1, r2, r3, r4));
    }

    @Test
    public void testFindActiveReservations() {
        List<Reservation> active = analytics.findActiveReservations();
        assertEquals(active.size(), 3);
        assertFalse(active.contains(r4));
    }

    @Test
    public void testFindReservationsInDateRange() {
        List<Reservation> june = analytics.findReservationsInDateRange(
                LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 30));
        assertEquals(june.size(), 3);
        assertEquals(june.get(0), r1);
    }

    @Test
    public void testGetCustomerNamesSorted() {
        List<String> names = analytics.getCustomerNamesSorted();
        assertEquals(names.size(), 3);
        assertEquals(names.get(0), "Ivan Petrenko");
        assertTrue(names.contains("Olena Shevchenko"));
    }

    @Test
    public void testCountReservationsByRoomType() {
        Map<String, Long> counts = analytics.countReservationsByRoomType();
        assertEquals(counts.get("standard"), Long.valueOf(1));
        assertEquals(counts.get("luxury"), Long.valueOf(2));
        assertEquals(counts.get("family"), Long.valueOf(1));
    }

    @Test
    public void testCalculateTotalRevenueOnlyActive() {
        double expected = r1.totalCost() + r2.totalCost() + r3.totalCost();
        assertEquals(analytics.calculateTotalRevenue(), expected, 0.001);
    }

    @Test
    public void testFindMostExpensiveReservation() {
        assertTrue(analytics.findMostExpensiveReservation().isPresent());
        assertEquals(analytics.findMostExpensiveReservation().get(), r2);
    }

    @Test
    public void testStatistics() {
        assertEquals(analytics.getTotalCostStatistics().getCount(), 4);
        assertTrue(analytics.getTotalCostStatistics().getMax() >= analytics.getTotalCostStatistics().getMin());
    }

    @Test
    public void testImperativeAndStreamGiveSameResult() {
        assertEquals(analytics.findByRoomTypeImperative("luxury"), analytics.findByRoomTypeStream("luxury"));
    }

    @Test
    public void testEmptyAnalytics() {
        BookingAnalytics empty = new BookingAnalytics(List.of());
        assertTrue(empty.findActiveReservations().isEmpty());
        assertEquals(empty.calculateTotalRevenue(), 0.0, 0.001);
        assertTrue(empty.findMostExpensiveReservation().isEmpty());
    }
}
