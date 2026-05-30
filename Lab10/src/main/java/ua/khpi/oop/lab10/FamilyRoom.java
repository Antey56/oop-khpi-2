package ua.khpi.oop.lab10;

public class FamilyRoom extends Room implements Reservable, Bookable {
    private final int capacity;

    public FamilyRoom(String roomNumber, double pricePerNight, int capacity) {
        super(roomNumber, "family", pricePerNight);
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String getDescription() {
        return "Family room for " + capacity + " people";
    }

    @Override
    public void reserve() {
        System.out.println("Family room reserved");
    }
}
