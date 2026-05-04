package ua.khpi.oop.lab01;

public class Hotel {
    private String name;
    private Room[] rooms;
    private int roomCount;

    public Hotel(String name, int capacity) {
        this.name = name;
        this.rooms = new Room[capacity];
        this.roomCount = 0;
    }

    public String getName() {
        return name;
    }

    public void addRoom(Room room) {
        if (roomCount < rooms.length && room != null) {
            rooms[roomCount] = room;
            roomCount++;
        }
    }

    public Room findAvailableRoom() {
        for (int i = 0; i < roomCount; i++) {
            if (rooms[i].isAvailable()) {
                return rooms[i];
            }
        }

        return null;
    }

    public void printRooms() {
        System.out.println("Hotel: " + name);
        System.out.println("Rooms:");

        for (int i = 0; i < roomCount; i++) {
            System.out.println(rooms[i]);
        }
    }

    @Override
    public String toString() {
        return "Hotel{name='" + name + "', roomCount=" + roomCount + '}';
    }
}