package ua.khpi.oop.lab05;

public class LuxuryRoom extends Room {
    private boolean hasJacuzzi;
    private double luxuryMultiplier;

    public LuxuryRoom(String roomNumber, double pricePerNight, boolean hasJacuzzi) {
        super(roomNumber, "luxury", pricePerNight);
        this.hasJacuzzi = hasJacuzzi;
        this.luxuryMultiplier = 1.5;
    }

    public boolean hasJacuzzi() {
        return hasJacuzzi;
    }

    @Override
    public double getPricePerNight() {
        return super.getPricePerNight() * luxuryMultiplier;
    }

    @Override
    public String getDescription() {
        return "Luxury room " + getRoomNumber() + ", jacuzzi: " + hasJacuzzi;
    }
}