package ua.khpi.oop.lab08;

import java.io.Serializable;

public class Hotel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Room[] rooms;
    private int roomCount;

    public Hotel(String name, int capacity) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Hotel name must not be blank");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }

        this.name = name;
        this.rooms = new Room[capacity];
        this.roomCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void addRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room must not be null");
        }
        if (roomCount >= rooms.length) {
            throw new IllegalStateException("Hotel capacity is full");
        }

        rooms[roomCount] = room;
        roomCount++;
    }

    public Room findAvailableRoom() {
        for (int i = 0; i < roomCount; i++) {
            if (rooms[i].isAvailable()) {
                return rooms[i];
            }
        }

        return null;
    }

    public void printRoomDescriptions() {
        for (int i = 0; i < roomCount; i++) {
            System.out.println(rooms[i].getDescription());
        }
    }

    @Override
    public String toString() {
        return "Hotel{name='" + name + "', roomCount=" + roomCount + '}';
    }
}