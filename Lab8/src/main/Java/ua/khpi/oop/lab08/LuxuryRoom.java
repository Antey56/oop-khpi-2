package ua.khpi.oop.lab08;

public class LuxuryRoom extends Room implements Reservable, Cleanable {
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
        return "Luxury room with jacuzzi";
    }

    @Override
    public void reserve() {
        System.out.println("Luxury room reserved");
    }

    @Override
    public void clean() {
        System.out.println("Luxury room cleaned");
    }
}