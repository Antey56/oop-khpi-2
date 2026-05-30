
package ua.khpi.oop.lab13;

public class BookingTextReport {
    private final int totalLines;
    private final int validReservations;
    private final int invalidLines;
    private final double activeRevenue;

    public BookingTextReport(int totalLines, int validReservations, int invalidLines, double activeRevenue) {
        this.totalLines = totalLines;
        this.validReservations = validReservations;
        this.invalidLines = invalidLines;
        this.activeRevenue = activeRevenue;
    }

    public int getTotalLines() { return totalLines; }
    public int getValidReservations() { return validReservations; }
    public int getInvalidLines() { return invalidLines; }
    public double getActiveRevenue() { return activeRevenue; }
}
