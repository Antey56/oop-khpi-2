
package ua.khpi.oop.lab11;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

import static org.testng.Assert.*;

public class HotelBookingRegistryTest {
    private HotelBookingRegistry registry;
    private Customer customer;
    private VipCustomer vipCustomer;
    private Reservation r1;
    private Reservation r2;
    private Reservation r3;

    @BeforeMethod
    public void setUp() {
        registry = new HotelBookingRegistry();
        customer = new Customer("C-001", "Olena Kovalenko", "+380501234567");
        vipCustomer = new VipCustomer("VIP-001", "Danylo Vintoniak", "+4915112345678", 3);

        Room standardRoom = new StandardRoom("101", 800.0, true);
        Room luxuryRoom = new LuxuryRoom("310", 2000.0, true);
        Room familyRoom = new FamilyRoom("205", 1200.0, 4);

        r1 = new Reservation("R-001", customer, standardRoom,
                LocalDate.of(2026, 6, 10), LocalDate.of(2026, 6, 12));
        r2 = new Reservation("R-002", vipCustomer, luxuryRoom,
                LocalDate.of(2026, 6, 12), LocalDate.of(2026, 6, 15));
        r3 = new Reservation("R-003", customer, familyRoom,
                LocalDate.of(2026, 6, 18), LocalDate.of(2026, 6, 20));
    }

    @Test
    public void testInitialState() {
        assertTrue(registry.isEmpty());
        assertEquals(registry.size(), 0);
    }

    @Test
    public void testAddAndFindById() {
        registry.addReservation(r1);
        registry.addReservation(r2);
        assertEquals(registry.size(), 2);
        assertSame(registry.findById("R-001"), r1);
        assertSame(registry.findById("R-002"), r2);
        assertNull(registry.findById("R-999"));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDuplicateReservationId() {
        registry.addReservation(r1);
        Reservation duplicate = new Reservation("R-001", vipCustomer, new LuxuryRoom("400", 2500.0, true),
                LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 3));
        registry.addReservation(duplicate);
    }

    @Test
    public void testUniqueCustomersSet() {
        registry.addReservation(r1);
        registry.addReservation(r3);
        assertEquals(registry.getUniqueCustomers().size(), 1);
        registry.addReservation(r2);
        assertEquals(registry.getUniqueCustomers().size(), 2);
    }

    @Test
    public void testRemoveReservationKeepsCollectionsConsistent() {
        registry.addReservation(r1);
        registry.addReservation(r2);
        Reservation removed = registry.removeReservation("R-001");
        assertSame(removed, r1);
        assertEquals(registry.size(), 1);
        assertFalse(registry.containsReservation("R-001"));
        assertFalse(registry.getProcessingQueueSnapshot().contains(r1));
    }

    @Test
    public void testQueueProcessingOrder() {
        registry.addReservation(r1);
        registry.addReservation(r2);
        assertSame(registry.pollNextForProcessing(), r1);
        assertSame(registry.pollNextForProcessing(), r2);
        assertNull(registry.pollNextForProcessing());
    }

    @Test
    public void testFindByCustomerAndRoomType() {
        registry.addReservation(r1);
        registry.addReservation(r2);
        registry.addReservation(r3);
        assertEquals(registry.findByCustomer(customer).size(), 2);
        assertEquals(registry.findByRoomType("luxury").size(), 1);
        assertSame(registry.findByRoomType("luxury").get(0), r2);
    }

    @Test
    public void testCancelReservation() {
        registry.addReservation(r1);
        assertTrue(registry.cancelReservation("R-001"));
        assertFalse(registry.findById("R-001").isActive());
        assertFalse(registry.cancelReservation("R-999"));
    }

    @Test
    public void testSortingByTotalCost() {
        registry.addReservation(r1);
        registry.addReservation(r2);
        registry.addReservation(r3);
        List<Reservation> sorted = registry.getReservationsSortedByTotalCostDescending();
        assertSame(sorted.get(0), r2);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testAllReservationsIsUnmodifiable() {
        registry.addReservation(r1);
        registry.getAllReservations().add(r2);
    }
}
