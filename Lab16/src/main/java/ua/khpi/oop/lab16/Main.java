package ua.khpi.oop.lab16;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] dailyOccupancy = {12, 15, 18, 20, 17, 14, 16};

        BookingStatistics javaStatistics = new BookingStatistics();
        NativeBookingStatistics nativeStatistics = new NativeBookingStatistics();

        double javaAverage = javaStatistics.average(dailyOccupancy);
        double nativeAverage = nativeStatistics.average(dailyOccupancy);

        System.out.println("Lab 16 JNI demo: average value calculation");
        System.out.println("Input values: " + Arrays.toString(dailyOccupancy));
        System.out.println("Java average: " + javaAverage);
        System.out.println("Native average: " + nativeAverage);
    }
}
