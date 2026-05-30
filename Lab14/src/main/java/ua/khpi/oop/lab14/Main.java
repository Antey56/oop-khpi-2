package ua.khpi.oop.lab14;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BookingThreadService service = new BookingThreadService();
        List<Reservation> reservations = createDemoReservations();

        ReservationCheckThread checkThread = new ReservationCheckThread(
                "ReservationCheckThread", reservations, service);
        ConfirmationTask confirmationTask = new ConfirmationTask(reservations, service);
        OccupancyReportTask reportTask = new OccupancyReportTask(reservations, service);

        Thread confirmationThread = new Thread(confirmationTask, "ConfirmationThread");
        Thread reportThread = new Thread(reportTask, "OccupancyReportThread");

        System.out.println("=== Lab 14: Basic multithreading in hotel booking system ===");
        System.out.println("Main thread starts worker threads using start().\n");

        checkThread.start();
        confirmationThread.start();
        reportThread.start();

        checkThread.join();
        confirmationThread.join();
        reportThread.join();

        System.out.println("\nAll worker threads finished after join().");
        System.out.println("Valid reservations: " + checkThread.getValidReservations().size());
        System.out.println("Confirmations: " + confirmationTask.getConfirmations().size());
        System.out.println();
        System.out.println(reportTask.getReport());
    }

    private static List<Reservation> createDemoReservations() {
        Customer customer1 = new Customer("C-001", "Anna Brown", "+491111111111");
        Customer customer2 = new VipCustomer("C-002", "Mark Stone", "+492222222222", 15);
        Customer customer3 = new Customer("C-003", "Helen Smith", "+493333333333");

        Room standard = new StandardRoom("101", 80.0, true);
        Room family = new FamilyRoom("202", 130.0, 4);
        Room luxury = new LuxuryRoom("303", 250.0, true);

        Reservation r1 = new Reservation("R-001", customer1, standard,
                LocalDate.of(2026, 5, 10), LocalDate.of(2026, 5, 13));
        Reservation r2 = new Reservation("R-002", customer2, luxury,
                LocalDate.of(2026, 5, 12), LocalDate.of(2026, 5, 15));
        Reservation r3 = new Reservation("R-003", customer3, family,
                LocalDate.of(2026, 5, 20), LocalDate.of(2026, 5, 22));
        r3.cancel();

        return List.of(r1, r2, r3);
    }
}
