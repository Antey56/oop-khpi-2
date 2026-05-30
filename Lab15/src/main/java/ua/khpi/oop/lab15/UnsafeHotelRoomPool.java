package ua.khpi.oop.lab15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnsafeHotelRoomPool {
    private final List<Room> availableRooms = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public UnsafeHotelRoomPool(List<Room> rooms) {
        availableRooms.addAll(rooms);
    }

    public boolean tryBook(BookingRequest request) {
        Room room = findAvailableRoom(request.getRoomType());
        if (room == null) {
            return false;
        }

        Thread.yield();
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

    private Room findAvailableRoom(String type) {
        for (Room room : availableRooms) {
            if (room.getRoomType().equalsIgnoreCase(type)) {
                return room;
            }
        }
        return null;
    }

    public int getAvailableRoomCount() { return availableRooms.size(); }
    public int getReservationCount() { return reservations.size(); }
    public List<Reservation> getReservations() { return Collections.unmodifiableList(reservations); }
}
