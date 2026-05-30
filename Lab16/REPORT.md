# Звіт до лабораторної роботи №16

## Тема

Автоматизована збірка, тестування і CI для Java-проєкту.

## Варіант

Варіант 3: обчислення середнього арифметичного значення елементів масиву.

## Мета роботи

Було підготовлено Java-проєкт, у якому одна операція реалізована двома способами: звичайним Java-кодом та native-кодом C++ через JNI. Також було додано автоматичні тести та GitHub Actions workflow.

## Структура проєкту

```text
src/main/java/ua/khpi/oop/lab16/BookingStatistics.java
src/main/java/ua/khpi/oop/lab16/NativeBookingStatistics.java
src/main/java/ua/khpi/oop/lab16/Main.java
src/test/java/ua/khpi/oop/lab16/BookingStatisticsTest.java
native/bookingstats.cpp
native-headers/ua_khpi_oop_lab16_NativeBookingStatistics.h
.github/workflows/ci.yml
pom.xml
README.md
REPORT.md
```

## Java-реалізація

Клас `BookingStatistics` обчислює середнє значення масиву `int[]`. Перед обчисленням перевіряється, що масив не дорівнює `null` і не є порожнім.

## Native-реалізація

Клас `NativeBookingStatistics` оголошує native-метод:

```java
public native double average(int[] values);
```

Реалізація цього методу знаходиться у файлі `native/bookingstats.cpp`. Через JNI отримується масив цілих чисел, обчислюється сума елементів і повертається середнє значення.

## Команда генерації header-файла

```bash
javac -h native-headers -d target/classes src/main/java/ua/khpi/oop/lab16/NativeBookingStatistics.java
```

## Команда збирання native-бібліотеки

```bash
clang++ -std=c++17 -shared -fPIC \
  -I"$JAVA_HOME/include" \
  -I"$JAVA_HOME/include/linux" \
  -Inative-headers \
  native/bookingstats.cpp \
  -o build/native/libbookingstats.so
```

## Завантаження native-бібліотеки

У Java використовується:

```java
System.loadLibrary("bookingstats");
```

Для Linux файл бібліотеки має назву `libbookingstats.so`, а шлях до каталогу задається через `java.library.path`.

## Тести

Тести перевіряють:

- Java-реалізацію;
- native-реалізацію;
- однаковість результатів Java та native для однакових вхідних даних;
- граничний випадок з порожнім масивом.

## GitHub Actions workflow

Workflow виконує такі кроки:

1. отримання коду з репозиторію;
2. встановлення JDK 17;
3. генерація JNI header-файла;
4. збирання native-бібліотеки через `clang++`;
5. компіляція Java-проєкту;
6. запуск тестів Maven.

## Технічна проблема

Можлива типова проблема: `UnsatisfiedLinkError: no bookingstats in java.library.path`. Вона виникає, якщо JVM не бачить каталог з файлом `libbookingstats.so`. Для усунення в `pom.xml` для Maven Surefire було задано `java.library.path=${project.basedir}/build/native`, а в команді запуску Main шлях передається через параметр `-Djava.library.path=build/native`.

## Висновок

Було створено проєкт із Java- та native-реалізацією однієї операції. JNI дозволив викликати C++ код із Java, а GitHub Actions забезпечує автоматичну перевірку збірки, native-бібліотеки та тестів після змін у репозиторії.
