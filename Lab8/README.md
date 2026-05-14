# Lab 3 — Модульне тестування (JUnit Jupiter)

## 📌 Опис
У цій лабораторній роботі реалізовано модульне тестування предметної моделі системи бронювання номерів у готелі.

Тестування виконано з використанням JUnit Jupiter та Maven.

## 🧱 Структура проєкту


src/
├── main/java/ua/khpi/oop/lab03/
│ ├── Room.java
│ ├── Guest.java
│ ├── Booking.java
│ └── Hotel.java
│
└── test/java/ua/khpi/oop/lab03/
├── RoomTest.java
├── GuestTest.java
├── BookingTest.java
└── HotelTest.java


## ⚙️ Використані технології
- Java 17
- Maven
- JUnit Jupiter

## ▶️ Запуск тестів

```bash
mvn clean test