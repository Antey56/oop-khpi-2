package ua.khpi.oop.lab10;

import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class BookingLinkTest {
    @Test(groups = {"smoke", "domain"})
    public void shouldStoreCustomerAndRoomWithConcreteTypes() {
        Customer customer = new Customer("C-001", "Olena Kovalenko");
        StandardRoom room = new StandardRoom("101", 800.0, true);

        BookingLink<Customer, StandardRoom> link = new BookingLink<>(customer, room);

        assertSame(link.getCustomer(), customer);
        assertSame(link.getResource(), room);
        assertTrue(link.getResource().hasBreakfast());
    }

    @Test(groups = {"domain"})
    public void shouldWorkWithVipCustomerAndLuxuryRoom() {
        VipCustomer customer = new VipCustomer("VIP-001", "Danylo Vintoniak", "+4915112345678", 3);
        LuxuryRoom room = new LuxuryRoom("310", 2000.0, true);

        BookingLink<VipCustomer, LuxuryRoom> link = new BookingLink<>(customer, room);

        assertEquals(link.getCustomer().getBonusLevel(), 3);
        assertTrue(link.getResource().hasJacuzzi());
    }

    @Test(groups = {"domain"})
    public void shouldCreateReservationFromGenericLinkWithDates() {
        Customer customer = new Customer("C-002", "Ivan Petrenko");
        FamilyRoom room = new FamilyRoom("205", 1200.0, 4);
        BookingLink<Customer, FamilyRoom> link = new BookingLink<>(customer, room);

        Reservation reservation = link.createReservation(
                "R-001",
                LocalDate.of(2026, 6, 10),
                LocalDate.of(2026, 6, 12)
        );

        assertEquals(reservation.getCustomer(), customer);
        assertEquals(reservation.getRoom(), room);
        assertEquals(reservation.getNights(), 2);
        assertEquals(reservation.totalCost(), 2400.0);
        assertTrue(room.isAvailable());
    }

    @Test(groups = {"negative"}, expectedExceptions = IllegalArgumentException.class)
    public void shouldRejectNullCustomer() {
        new BookingLink<Customer, StandardRoom>(null, new StandardRoom("101", 800.0, true));
    }
}
