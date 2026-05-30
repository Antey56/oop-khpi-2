package ua.khpi.oop.lab14;

public class BookingLink<TCustomer extends Customer, TResource extends Room> {
    private final TCustomer customer;
    private final TResource resource;

    public BookingLink(TCustomer customer, TResource resource) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer must not be null");
        }
        if (resource == null) {
            throw new IllegalArgumentException("Resource must not be null");
        }
        this.customer = customer;
        this.resource = resource;
    }

    public TCustomer getCustomer() {
        return customer;
    }

    public TResource getResource() {
        return resource;
    }

    public Reservation createReservation(String reservationId, int nights) {
        return new Reservation(reservationId, customer, resource, nights);
    }

    public Reservation createReservation(String reservationId, java.time.LocalDate checkInDate, java.time.LocalDate checkOutDate) {
        return new Reservation(reservationId, customer, resource, checkInDate, checkOutDate);
    }

    @Override
    public String toString() {
        return "BookingLink{customer=" + customer.getFullName() +
                ", resource=" + resource.getRoomNumber() + '}';
    }
}
