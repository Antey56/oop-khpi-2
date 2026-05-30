
package ua.khpi.oop.lab14;

import java.util.List;

/**
 * Runnable task that builds an occupancy report in a separate thread.
 */
public class OccupancyReportTask implements Runnable {
    private final List<Reservation> reservations;
    private final BookingThreadService service;
    private String report = "";

    public OccupancyReportTask(List<Reservation> reservations, BookingThreadService service) {
        this.reservations = List.copyOf(reservations);
        this.service = service;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            System.out.println("[" + threadName + "] building occupancy report...");
            Thread.sleep(200);
            report = service.buildOccupancyReport(reservations);
            System.out.println("[" + threadName + "] report is ready");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getReport() {
        return report;
    }
}
