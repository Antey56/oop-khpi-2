# Lab 16 — Automated Build, Testing and CI for Java Project

Variant: **3 — arithmetic average value of integer array elements**.

The project continues the hotel booking topic from previous labs. For Lab 16 the operation is intentionally small: average daily room occupancy is calculated in two versions:

- `BookingStatistics` — pure Java implementation;
- `NativeBookingStatistics` + `native/bookingstats.cpp` — JNI/C++ implementation.

## Requirements

- JDK 17+
- Maven 3.8+
- clang++ or compatible C++ compiler
- GitHub Actions for CI

## Generate JNI header

```bash
mkdir -p native-headers target/classes
javac -h native-headers \
  -d target/classes \
  src/main/java/ua/khpi/oop/lab16/NativeBookingStatistics.java
```

## Build native library on Linux

```bash
mkdir -p build/native
clang++ -std=c++17 -shared -fPIC \
  -I"$JAVA_HOME/include" \
  -I"$JAVA_HOME/include/linux" \
  -Inative-headers \
  native/bookingstats.cpp \
  -o build/native/libbookingstats.so
```

## Build Java project

```bash
mvn compile
```

## Run tests

The Maven Surefire plugin passes `java.library.path=build/native` to tests.

```bash
mvn test
```

## Run Main

```bash
mvn compile
java -Djava.library.path=build/native \
  -cp target/classes \
  ua.khpi.oop.lab16.Main
```

## CI

GitHub Actions workflow is located at `.github/workflows/ci.yml`. It runs on `push` and `pull_request`, sets up JDK, generates JNI headers, builds the native library, compiles Java code and runs tests.
