package ua.khpi.oop.lab08;

public class StandardRoom extends Room implements Reservable {
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
        return "Standard room";
    }

    @Override
    public void reserve() {
        System.out.println("Standard room reserved");
    }
}