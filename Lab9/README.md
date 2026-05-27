# Лабораторна робота №9 — Узагальнення (Generics) в Java

## Варіант

Варіант №3 — **Система бронювання**.

Предметна область продовжує попередні лабораторні роботи: реалізовано модель бронювання номерів готелю.

## Що реалізовано

У роботі додано використання generics:

1. `BookingLink<TCustomer extends Customer, TResource extends Room>` — generic-клас для типобезпечного зв'язування клієнта з ресурсом бронювання.
2. `ReservationRecord<TReservation extends Reservation, TMeta>` — generic-клас для збереження бронювання разом із метаданими.
3. `BookingSchedule<TReservation extends Reservation>` — generic-клас для збереження бронювань і перевірки перетину дат.
4. `BookingUtils.findMostExpensiveRoom(List<TRoom>)` — generic-метод з обмеженням `TRoom extends Room`.
5. `BookingUtils.findFirstAvailableRoom(List<TRoom>, BookingSchedule<? extends Reservation>, LocalDate, LocalDate)` — generic-метод для пошуку номера, вільного у вибраний період.

## Навіщо тут generics

Узагальнення дозволяє працювати з різними типами клієнтів, номерів і бронювань без приведення типів через `Object`.
Також у бронюванні додано дати `checkInDate` і `checkOutDate`, щоб система могла відрізняти нормальні послідовні бронювання від помилкового подвійного бронювання одного номера на той самий період.
Наприклад:

```java
BookingLink<Customer, StandardRoom> standardLink = new BookingLink<>(customer, standardRoom);
BookingLink<VipCustomer, LuxuryRoom> vipLink = new BookingLink<>(vipCustomer, luxuryRoom);
```

У першому випадку ресурсом є `StandardRoom`, у другому — `LuxuryRoom`, але використовується один і той самий generic-компонент.

## Структура

- `src/main/java/ua/khpi/oop/lab09/` — вихідний код;
- `src/test/java/ua/khpi/oop/lab09/` — тести TestNG;
- `docs/class-diagram.puml` — UML-діаграма PlantUML;
- `REPORT.md` — короткий звіт.

## Перевірка перетину бронювань

Клас `BookingSchedule<TReservation extends Reservation>` не дозволяє додати бронювання, якщо той самий номер уже зайнятий у вибраний період.
Наприклад, бронювання 10.06.2026–12.06.2026 і 11.06.2026–13.06.2026 для одного номера вважаються конфліктом, а 10.06.2026–12.06.2026 і 12.06.2026–14.06.2026 — ні, бо друге починається в день виїзду першого клієнта.

## Запуск

```bash
mvn test
```

```bash
mvn exec:java -Dexec.mainClass="ua.khpi.oop.lab09.Main"
```
