# Лабораторна робота №10 — Узагальнені контейнери та ітератори в Java

## Варіант

Варіант №3 — **Система бронювання**.

Предметна область продовжує попередні лабораторні роботи: модель бронювання номерів готелю. Збережено основні класи попередньої роботи: `Customer`, `VipCustomer`, `Room`, `StandardRoom`, `FamilyRoom`, `LuxuryRoom`, `Reservation`, `ReservationRecord`, `BookingLink`, `BookingSchedule`.

## Що реалізовано

У роботі реалізовано власний узагальнений контейнер:

1. `ReservationContainer<T>` — самописний generic-контейнер на основі масиву.
2. Контейнер підтримує операції `add`, `get`, `set`, `remove`, `contains`, `clear`, `size`, `capacity`, `isEmpty`.
3. Для контейнера реалізовано власний внутрішній ітератор `ReservationContainerIterator`.
4. `ReservationContainer<T>` реалізує `Iterable<T>`, тому підтримує обхід через `for-each`.
5. `BookingSchedule<TReservation extends Reservation>` використовує `ReservationContainer<TReservation>` як внутрішнє сховище бронювань.
6. У `Main` показано додавання, доступ, видалення, обхід через явний ітератор і обхід через `for-each`.
7. Додано тести для контейнера та збережено тести попередньої моделі.

## Чому контейнер generic

Контейнер `ReservationContainer<T>` може зберігати різні типи об'єктів без приведення типів через `Object`. Наприклад:

```java
ReservationContainer<Reservation> reservations = new ReservationContainer<>();
ReservationContainer<ReservationRecord<Reservation, String>> records = new ReservationContainer<>();
ReservationContainer<Room> rooms = new ReservationContainer<>();
```

Це робить контейнер типобезпечним і придатним для повторного використання.

## Структура

- `src/main/java/ua/khpi/oop/lab10/` — вихідний код;
- `src/test/java/ua/khpi/oop/lab10/` — тести TestNG;
- `docs/class-diagram.puml` — UML-діаграма PlantUML;
- `REPORT.md` — короткий звіт;
- `pom.xml` — Maven-конфігурація.

## Запуск

```bash
mvn test
```

```bash
mvn exec:java -Dexec.mainClass="ua.khpi.oop.lab10.Main"
```
