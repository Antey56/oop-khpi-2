package ua.khpi.oop.lab08;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class HotelFileManagerTest {

    @Test
    public void hotelCanBeSavedAndLoaded() throws Exception {
        Hotel hotel = new Hotel("Kharkiv Palace", 3);

        hotel.addRoom(new StandardRoom("101", 800.0, true));
        hotel.addRoom(new FamilyRoom("205", 1200.0, 4));
        hotel.addRoom(new LuxuryRoom("310", 2000.0, true));

        String fileName = "test-hotel.dat";

        HotelFileManager.saveHotel(hotel, fileName);
        Hotel loadedHotel = HotelFileManager.loadHotel(fileName);

        Assert.assertNotNull(loadedHotel);
        Assert.assertEquals(loadedHotel.getName(), "Kharkiv Palace");
        Assert.assertEquals(loadedHotel.getRoomCount(), 3);

        new File(fileName).delete();
    }
}