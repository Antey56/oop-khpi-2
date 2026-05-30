package ua.khpi.oop.lab16;

/**
 * JNI wrapper for the native C++ implementation of the same operation.
 */
public class NativeBookingStatistics {
    static {
        System.loadLibrary("bookingstats");
    }

    public native double average(int[] values);
}
