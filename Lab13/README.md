# Laboratory work 13: Text processing and regular expressions

Topic: hotel booking text confirmations based on the previous hotel booking project.

The program reads booking confirmations from `src/main/resources/booking-confirmations.txt`, normalizes text lines, validates the format with a regular expression, extracts fields with `Pattern` and `Matcher`, creates domain objects (`Reservation`, `Customer`, `Room`) and builds a report using `StringBuilder`.

## Main components

- `BookingTextProcessor` — text-processing service.
- `BookingTextReport` — summary of parsed text records.
- `Reservation`, `Customer`, `Room` and subclasses — domain model kept from previous labs.
- `BookingAnalytics` — reused analytics service from Lab 12 for parsed reservations.
- `Main` — demonstration program.
- `BookingTextProcessorTest` — TestNG unit tests.

## Run

```bash
mvn test
mvn exec:java -Dexec.mainClass="ua.khpi.oop.lab13.Main"
```
