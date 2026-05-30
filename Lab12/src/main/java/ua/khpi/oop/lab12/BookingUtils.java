package ua.khpi.oop.lab12;

import java.time.LocalDate;
import java.util.List;

public final class BookingUtils {
    private BookingUtils() {
    }

    public static <TRoom extends Room> TRoom findMostExpensiveRoom(List<TRoom> rooms) {
        if (rooms == null || rooms.isEmpty()) {
            return null;
        }

        TRoom mostExpensive = rooms.get(0);
        for (TRoom room : rooms) {
            if (room.getPricePerNight() > mostExpensive.getPricePerNight()) {
                mostExpensive = room;
            }
        }
        return mostExpensive;
    }

    public static <TRoom extends Room> TRoom findFirstAvailableRoom(List<TRoom> rooms) {
        if (rooms == null || rooms.isEmpty()) {
            return null;
        }

        for (TRoom room : rooms) {
            if (room.isAvailable()) {
                return room;
            }
        }
        return null;
    }

    public static <TRoom extends Room> TRoom findFirstAvailableRoom(
            List<TRoom> rooms,
            BookingSchedule<? extends Reservation> schedule,
            LocalDate checkInDate,
            LocalDate checkOutDate) {
        if (rooms == null || rooms.isEmpty()) {
            return null;
        }
        if (schedule == null) {
            throw new IllegalArgumentException("Booking schedule must not be null");
        }

        for (TRoom room : rooms) {
            if (room.isAvailable() && schedule.isRoomAvailable(room, checkInDate, checkOutDate)) {
                return room;
            }
        }
        return null;
    }
}
