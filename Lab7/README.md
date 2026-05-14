README — Лабораторна робота №7
Тема

Інтерфейси у Java

Що було змінено у порівнянні з ЛР6

У проєкті було реалізовано роботу з інтерфейсами.

Нові інтерфейси

Було створено:

Reservable
void reserve();
Bookable
void book();
Cleanable
void clean();
Реалізація інтерфейсів
Клас	Інтерфейси
StandardRoom	Reservable
FamilyRoom	Reservable, Bookable
LuxuryRoom	Reservable, Cleanable
Що демонструє ЛР7
множинну реалізацію інтерфейсів;
інтерфейсну абстракцію;
поліморфізм через інтерфейси;
роботу instanceof.
Оновлений Main

У Main.java було додано демонстрацію:

Reservable reservable = new LuxuryRoom(...);
reservable.reserve();

Cleanable cleanable = new LuxuryRoom(...);
cleanable.clean();
Тестування

Додано перевірки:

роботи інтерфейсів;
виклику методів через інтерфейс;
поліморфної поведінки.
Maven

Для запуску:

mvn clean test

Для запуску програми:

mvn exec:java "-Dexec.mainClass=ua.khpi.oop.lab07.Main"
