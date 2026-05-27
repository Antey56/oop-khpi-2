# Звіт до лабораторної роботи №9

## Тема

Узагальнення (Generics) в Java.

## Варіант

Варіант №3 — система бронювання.

## Мета роботи

Було опрацьовано використання узагальнених типів у Java, проєктування generic-класів і generic-методів, а також застосування обмежень параметрів типів для створення типобезпечної моделі предметної області.

## Опис предметної області

Предметна область — система бронювання номерів готелю. У моделі використовуються клієнти, різні типи номерів і бронювання. Номери представлені базовим абстрактним класом `Room` та його нащадками: `StandardRoom`, `FamilyRoom`, `LuxuryRoom`. Клієнти представлені класами `Customer` і `VipCustomer`. Бронювання описується класом `Reservation`. Для бронювання додано дати заїзду та виїзду: `checkInDate` і `checkOutDate`. Це потрібно, щоб система могла перевіряти, чи не бронюють два клієнти один і той самий номер на періоди, що перетинаються.

## Generic-клас

Основним generic-класом є:

```java
BookingLink<TCustomer extends Customer, TResource extends Room>
```

Цей клас зв'язує клієнта з ресурсом бронювання. Обмеження `TCustomer extends Customer` гарантує, що як клієнт може використовуватися тільки об'єкт клієнта або його нащадок. Обмеження `TResource extends Room` гарантує, що ресурсом бронювання може бути тільки номер готелю або його спеціалізований тип.

Додатково реалізовано:

```java
ReservationRecord<TReservation extends Reservation, TMeta>
```

Цей клас дозволяє зберігати бронювання з різними типами метаданих, наприклад статусом `String` або знижкою `Double`.

Також реалізовано:

```java
BookingSchedule<TReservation extends Reservation>
```

Цей generic-клас зберігає список бронювань і не дозволяє додати нове бронювання, якщо для того самого номера вже існує активне бронювання з перетином дат. Це виправляє логічну помилку предметної області: без дат програма могла б забороняти всі повторні бронювання номера або, навпаки, не помічати подвійне бронювання на один день.

## Generic-методи

У класі `BookingUtils` реалізовано generic-методи:

```java
public static <TRoom extends Room> TRoom findMostExpensiveRoom(List<TRoom> rooms)
public static <TRoom extends Room> TRoom findFirstAvailableRoom(List<TRoom> rooms)
public static <TRoom extends Room> TRoom findFirstAvailableRoom(List<TRoom> rooms, BookingSchedule<? extends Reservation> schedule, LocalDate checkInDate, LocalDate checkOutDate)
```

Обмеження `TRoom extends Room` потрібне тому, що методи використовують поведінку класу `Room`: `getPricePerNight()` та `isAvailable()`.

## Приклади конкретизації

```java
BookingLink<Customer, StandardRoom> standardLink = new BookingLink<>(customer, standardRoom);
BookingLink<VipCustomer, LuxuryRoom> vipLink = new BookingLink<>(vipCustomer, luxuryRoom);
```

Також generic-запис використано з різними типами метаданих:

```java
ReservationRecord<Reservation, String> recordWithStatus = new ReservationRecord<>(reservation, "confirmed");
ReservationRecord<Reservation, Double> recordWithDiscount = new ReservationRecord<>(reservation, 0.10);
```

## UML-діаграма

UML-діаграма розміщена у файлі `docs/class-diagram.puml`. На ній показано ієрархію класів номерів, клієнтів, клас бронювання, generic-класи `BookingLink` і `ReservationRecord`, а також службовий клас `BookingUtils`.

## Тестування

Було реалізовано тести TestNG для:

- створення generic-зв'язку клієнта з номером;
- використання різних конкретизацій generic-класу;
- створення бронювання через generic-компонент;
- перевірки неможливості подвійного бронювання одного номера на перетин дат;
- перевірки можливості повторного бронювання номера на непересічні дати;
- пошуку найдорожчого номера generic-методом;
- пошуку першого доступного номера;
- перевірки generic-запису з різними типами метаданих.

## Висновок

Було реалізовано продовження системи бронювання з використанням generics. Узагальнення застосовано не формально, а для типобезпечного зв'язування клієнтів і ресурсів бронювання. Додатково було введено дати заїзду та виїзду, а також generic-розклад бронювань для перевірки перетину періодів. Використання обмежень параметрів типів дозволило зберегти доступ до методів предметної області та уникнути небезпечних приведень типів. У результаті API стало більш гнучким, але залишилося зрозумілим і пов'язаним з моделлю готельного бронювання.
