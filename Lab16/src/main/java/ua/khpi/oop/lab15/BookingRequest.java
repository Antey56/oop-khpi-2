package ua.khpi.oop.lab15;

import java.time.LocalDate;
import java.util.Objects;

public class BookingRequest {
    private final String requestId;
    private final Customer customer;
    private final String roomType;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;

    public BookingRequest(String requestId, Customer customer, String roomType,
                          LocalDate checkInDate, LocalDate checkOutDate) {
        if (requestId == null || requestId.isBlank()) {
            throw new IllegalArgumentException("Request id must not be blank");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer must not be null");
        }
        if (roomType == null || roomType.isBlank()) {
            throw new IllegalArgumentException("Room type must not be blank");
        }
        if (checkInDate == null || checkOutDate == null || !checkOutDate.isAfter(checkInDate)) {
            throw new IllegalArgumentException("Invalid booking dates");
        }
        this.requestId = requestId;
        this.customer = customer;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getRequestId() { return requestId; }
    public Customer getCustomer() { return customer; }
    public String getRoomType() { return roomType; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BookingRequest other)) return false;
        return Objects.equals(requestId, other.requestId);
    }

    @Override
    public int hashCode() { return Objects.hash(requestId); }
}
