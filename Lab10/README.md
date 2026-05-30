# Лабораторна робота №10

## Тема
Узагальнені контейнери та ітератори в Java.

## Варіант
Варіант 2 — система бронювання.

## Короткий опис
Проєкт демонструє реалізацію власного узагальненого контейнера `ReservationContainer<T>` для зберігання об'єктів системи бронювання готелю.

Контейнер:
- реалізований на основі внутрішнього масиву;
- не використовує `ArrayList` як основне сховище;
- підтримує `add`, `get`, `set`, `remove`, `size`, `isEmpty`, `contains`, `indexOf`, `clear`;
- має автоматичне розширення місткості;
- реалізує `Iterable<T>`;
- має власний ітератор.

## Основні класи
- `Room` — номер готелю.
- `Customer` — клієнт.
- `Reservation` — бронювання.
- `ReservationContainer<T>` — власний generic-контейнер.
- `ReservationSchedule<T extends Reservation>` — спеціалізований розклад бронювань із перевіркою конфліктів.

## Запуск

```bash
mvn clean test
```

```bash
mvn exec:java
```

## Структура
```text
Lab10
├── src/main/java/ua/khpi/oop/lab10
│   ├── Customer.java
│   ├── Main.java
│   ├── Reservation.java
│   ├── ReservationContainer.java
│   ├── ReservationSchedule.java
│   └── Room.java
├── src/test/java/ua/khpi/oop/lab10
│   ├── ReservationContainerTest.java
│   └── ReservationScheduleTest.java
├── docs
│   └── class-diagram.puml
├── pom.xml
├── README.md
└── REPORT.md
```
