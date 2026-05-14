package ua.khpi.oop.lab08;

import java.io.*;

public class HotelFileManager {
    public static void saveHotel(Hotel hotel, String fileName) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(hotel);
        }
    }

    public static Hotel loadHotel(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Hotel) in.readObject();
        }
    }
}