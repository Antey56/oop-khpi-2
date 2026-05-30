
package ua.khpi.oop.lab13;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> lines = readResourceLines("booking-confirmations.txt");
        BookingTextProcessor processor = new BookingTextProcessor();

        List<Reservation> reservations = processor.parseValidReservations(lines);
        BookingAnalytics analytics = new BookingAnalytics(reservations);

        System.out.println("=== Lab 13: Text processing and regular expressions ===
");
        System.out.println(processor.buildReport(lines));

        System.out.println("Room type summary from parsed objects:");
        System.out.println(analytics.buildRoomTypeSummaryReport());
    }

    private static List<String> readResourceLines(String resourceName) throws IOException, URISyntaxException {
        URL resource = Main.class.getClassLoader().getResource(resourceName);
        if (resource == null) {
            throw new IOException("Resource not found: " + resourceName);
        }
        return Files.readAllLines(Path.of(resource.toURI()));
    }
}
