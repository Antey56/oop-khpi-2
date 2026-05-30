package ua.khpi.oop.lab15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedHotelRoomPool {
    private final List<Room> availableRooms = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();
    private final Object lock = new Object();

    public SynchronizedHotelRoomPool(List<Room> rooms) {
        availableRooms.addAll(rooms);
    }

    public boolean tryBook(BookingRequest request) {
        synchronized (lock) {
            Room room = findAvailableRoom(request.getRoomType());
            if (room == null) {
                return false;
            }
            availableRooms.remove(room);
            room.book();
            reservations.add(new Reservation(
                    "RES-" + request.getRequestId(),
                    request.getCustomer(),
                    room,
                    request.getCheckInDate(),
                    request.getCheckOutDate()
            ));
            return true;
        }
    }

    private Room findAvailableRoom(String type) {
        for (Room room : availableRooms) {
            if (room.getRoomType().equalsIgnoreCase(type)) {
                return room;
            }
        }
        return null;
    }

    public int getAvailableRoomCount() {
        synchronized (lock) { return availableRooms.size(); }
    }

    public int getReservationCount() {
        synchronized (lock) { return reservations.size(); }
    }

    public List<Reservation> getReservations() {
        synchronized (lock) { return Collections.unmodifiableList(new ArrayList<>(reservations)); }
    }
}
