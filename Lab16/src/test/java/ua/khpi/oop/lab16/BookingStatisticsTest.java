package ua.khpi.oop.lab16;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BookingStatisticsTest {
    private final BookingStatistics javaStatistics = new BookingStatistics();
    private final NativeBookingStatistics nativeStatistics = new NativeBookingStatistics();

    @Test
    public void javaImplementationCalculatesAverage() {
        assertEquals(javaStatistics.average(new int[]{10, 20, 30}), 20.0, 0.0001);
    }

    @Test
    public void nativeImplementationCalculatesAverage() {
        assertEquals(nativeStatistics.average(new int[]{10, 20, 30}), 20.0, 0.0001);
    }

    @Test
    public void javaAndNativeResultsAreEqual() {
        int[][] cases = {
                {12, 15, 18, 20, 17},
                {0, 0, 0},
                {-5, 5, 10},
                {100},
                {1, 2, 2}
        };

        for (int[] testCase : cases) {
            assertEquals(nativeStatistics.average(testCase), javaStatistics.average(testCase), 0.0001);
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void javaImplementationRejectsEmptyArray() {
        javaStatistics.average(new int[]{});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void nativeImplementationRejectsEmptyArray() {
        nativeStatistics.average(new int[]{});
    }
}
