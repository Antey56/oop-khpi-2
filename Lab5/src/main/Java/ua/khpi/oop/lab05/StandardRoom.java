package ua.khpi.oop.lab05;

public class StandardRoom extends Room {
    private boolean hasBreakfast;

    public StandardRoom(String roomNumber, double pricePerNight, boolean hasBreakfast) {
        super(roomNumber, "standard", pricePerNight);
        this.hasBreakfast = hasBreakfast;
    }

    public boolean hasBreakfast() {
        return hasBreakfast;
    }

    @Override
    public String getDescription() {
        return "Standard room " + getRoomNumber() + ", breakfast: " + hasBreakfast;
    }
}