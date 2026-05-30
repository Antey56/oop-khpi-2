
# Laboratory work 14 — Basic multithreading in Java

Topic: **Hotel booking system**.

The project continues the previous hotel-booking model and demonstrates basic Java multithreading.

Implemented requirements:

- domain classes: `Customer`, `VipCustomer`, `Room`, `StandardRoom`, `FamilyRoom`, `LuxuryRoom`, `Reservation`;
- `ReservationCheckThread extends Thread`;
- `ConfirmationTask implements Runnable`;
- `OccupancyReportTask implements Runnable`;
- launching worker threads through `start()`;
- waiting for completion through `join()`;
- `Thread.sleep()` for step-by-step work simulation;
- named threads and diagnostic console output;
- deterministic unit tests for service logic and task result generation.

Run:

```bash
mvn test
mvn exec:java -Dexec.mainClass="ua.khpi.oop.lab14.Main"
```
