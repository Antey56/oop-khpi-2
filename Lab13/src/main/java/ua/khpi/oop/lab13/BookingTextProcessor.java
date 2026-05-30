
package ua.khpi.oop.lab13;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Lab 13 service: reads textual booking confirmations, normalizes strings,
 * validates records with regular expressions and converts lines to Reservation objects.
 */
public class BookingTextProcessor {
    private static final Pattern BOOKING_LINE_PATTERN = Pattern.compile(
            "^(\\d{4}-\\d{2}-\\d{2})\\s+" +
            "(BOOK-R-\\d{3})\\s+" +
            "CUSTOMER=(C-\\d{3})\\s+" +
            "NAME=\\\"([^\\\"]+)\\\"\\s+" +
            "PHONE=([+]?\\d{10,15})\\s+" +
            "ROOM=(\\d{3})\\s+" +
            "TYPE=(standard|family|luxury)\\s+" +
            "PRICE=(\\d+(?:\\.\\d{1,2})?)\\s+" +
            "CHECKIN=(\\d{4}-\\d{2}-\\d{2})\\s+" +
            "CHECKOUT=(\\d{4}-\\d{2}-\\d{2})\\s+" +
            "STATUS=(ACTIVE|CANCELLED)$"
    );

    public static String normalizeWhitespace(String line) {
        if (line == null) {
            return "";
        }
        return line.trim().replaceAll("\\s+", " ");
    }

    public boolean isBookingCodeValid(String code) {
        return code != null && code.matches("BOOK-R-\\d{3}");
    }

    public Reservation parseLine(String rawLine) {
        String normalized = normalizeWhitespace(rawLine);
        Matcher matcher = BOOKING_LINE_PATTERN.matcher(normalized);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid booking confirmation line: " + rawLine);
        }

        String reservationId = matcher.group(2).replace("BOOK-", "");
        String customerId = matcher.group(3);
        String customerName = matcher.group(4);
        String phone = matcher.group(5);
        String roomNumber = matcher.group(6);
        String roomType = matcher.group(7);
        double price = Double.parseDouble(matcher.group(8));
        LocalDate checkIn = LocalDate.parse(matcher.group(9));
        LocalDate checkOut = LocalDate.parse(matcher.group(10));
        String status = matcher.group(11);

        Customer customer = new Customer(customerId, customerName, phone);
        Room room = createRoom(roomNumber, roomType, price);
        Reservation reservation = new Reservation(reservationId, customer, room, checkIn, checkOut);

        if ("CANCELLED".equals(status)) {
            reservation.cancel();
        }

        return reservation;
    }

    public List<Reservation> parseValidReservations(List<String> rawLines) {
        List<Reservation> result = new ArrayList<>();
        for (String line : rawLines) {
            String normalized = normalizeWhitespace(line);
            if (normalized.isBlank()) {
                continue;
            }
            try {
                result.add(parseLine(normalized));
            } catch (IllegalArgumentException ignored) {
                // Invalid lines are counted in the report and skipped here.
            }
        }
        return result;
    }

    public BookingTextReport summarize(List<String> rawLines) {
        int total = 0;
        int invalid = 0;
        double revenue = 0.0;
        int valid = 0;

        for (String rawLine : rawLines) {
            String normalized = normalizeWhitespace(rawLine);
            if (normalized.isBlank()) {
                continue;
            }
            total++;
            try {
                Reservation reservation = parseLine(normalized);
                valid++;
                if (reservation.isActive()) {
                    revenue += reservation.totalCost();
                }
            } catch (IllegalArgumentException ex) {
                invalid++;
            }
        }
        return new BookingTextReport(total, valid, invalid, revenue);
    }

    public String buildReport(List<String> rawLines) {
        List<Reservation> reservations = parseValidReservations(rawLines);
        BookingTextReport summary = summarize(rawLines);
        StringBuilder report = new StringBuilder();

        report.append("Hotel booking text report\n");
        report.append("=========================\n");
        report.append("Total text lines: ").append(summary.getTotalLines()).append('\n');
        report.append("Valid reservations: ").append(summary.getValidReservations()).append('\n');
        report.append("Invalid lines: ").append(summary.getInvalidLines()).append('\n');
        report.append("Active revenue: ").append(String.format("%.2f", summary.getActiveRevenue())).append('\n');
        report.append('\n');
        report.append("Parsed reservations:\n");

        for (Reservation reservation : reservations) {
            report.append("- ")
                    .append(reservation.getReservationId())
                    .append(" | ")
                    .append(reservation.getCustomer().getFullName())
                    .append(" | room ")
                    .append(reservation.getRoom().getRoomNumber())
                    .append(" | ")
                    .append(reservation.getCheckInDate())
                    .append(" -> ")
                    .append(reservation.getCheckOutDate())
                    .append(" | status=")
                    .append(reservation.isActive() ? "ACTIVE" : "CANCELLED")
                    .append('\n');
        }

        return report.toString();
    }

    private Room createRoom(String roomNumber, String roomType, double price) {
        return switch (roomType.toLowerCase()) {
            case "standard" -> new StandardRoom(roomNumber, price, true);
            case "family" -> new FamilyRoom(roomNumber, price, 4);
            case "luxury" -> new LuxuryRoom(roomNumber, price, true);
            default -> throw new IllegalArgumentException("Unsupported room type: " + roomType);
        };
    }
}
