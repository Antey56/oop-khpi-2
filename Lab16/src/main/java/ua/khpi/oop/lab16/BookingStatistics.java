package ua.khpi.oop.lab16;

/**
 * Java implementation of variant 3: arithmetic average of integer array values.
 * In the hotel booking context the values can represent daily room occupancy counts.
 */
public class BookingStatistics {

    public double average(int[] values) {
        validate(values);
        long sum = 0;
        for (int value : values) {
            sum += value;
        }
        return (double) sum / values.length;
    }

    static void validate(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Input array must not be null");
        }
        if (values.length == 0) {
            throw new IllegalArgumentException("Input array must not be empty");
        }
    }
}
