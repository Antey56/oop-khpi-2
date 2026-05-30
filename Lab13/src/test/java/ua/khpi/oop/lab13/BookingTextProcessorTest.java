
package ua.khpi.oop.lab13;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class BookingTextProcessorTest {
    private final BookingTextProcessor processor = new BookingTextProcessor();

    @Test
    public void normalizesWhitespace() {
        String normalized = BookingTextProcessor.normalizeWhitespace(
                "  2026-06-01   BOOK-R-001   CUSTOMER=C-001  NAME="Ivan Petrenko"  PHONE=+380501111111  ROOM=101 TYPE=standard PRICE=80.00 CHECKIN=2026-06-01 CHECKOUT=2026-06-04 STATUS=ACTIVE  "
        );
        assertFalse(normalized.contains("  "));
        assertTrue(normalized.startsWith("2026-06-01 BOOK-R-001"));
    }

    @Test
    public void validatesBookingCode() {
        assertTrue(processor.isBookingCodeValid("BOOK-R-001"));
        assertFalse(processor.isBookingCodeValid("R-001"));
    }

    @Test
    public void parsesValidBookingLine() {
        Reservation reservation = processor.parseLine(
                "2026-06-01 BOOK-R-001 CUSTOMER=C-001 NAME="Ivan Petrenko" PHONE=+380501111111 ROOM=101 TYPE=standard PRICE=80.00 CHECKIN=2026-06-01 CHECKOUT=2026-06-04 STATUS=ACTIVE"
        );

        assertEquals(reservation.getReservationId(), "R-001");
        assertEquals(reservation.getCustomer().getFullName(), "Ivan Petrenko");
        assertEquals(reservation.getRoom().getRoomNumber(), "101");
        assertEquals(reservation.getNights(), 3);
        assertTrue(reservation.isActive());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void rejectsInvalidBookingLine() {
        processor.parseLine("wrong text line");
    }

    @Test
    public void parsesCancelledReservation() {
        Reservation reservation = processor.parseLine(
                "2026-06-05 BOOK-R-005 CUSTOMER=C-002 NAME="Olena Shevchenko" PHONE=+380502222222 ROOM=101 TYPE=standard PRICE=80.00 CHECKIN=2026-07-05 CHECKOUT=2026-07-08 STATUS=CANCELLED"
        );
        assertFalse(reservation.isActive());
    }

    @Test
    public void buildsTextReport() {
        List<String> lines = List.of(
                "2026-06-01 BOOK-R-001 CUSTOMER=C-001 NAME="Ivan Petrenko" PHONE=+380501111111 ROOM=101 TYPE=standard PRICE=80.00 CHECKIN=2026-06-01 CHECKOUT=2026-06-04 STATUS=ACTIVE",
                "invalid line"
        );

        String report = processor.buildReport(lines);

        assertTrue(report.contains("Valid reservations: 1"));
        assertTrue(report.contains("Invalid lines: 1"));
        assertTrue(report.contains("R-001"));
    }
}
