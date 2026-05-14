package ua.khpi.oop.lab08;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RoomPolymorphismTest {

    @Test
    public void polymorphismWorks() {
        Room room = new LuxuryRoom("301", 2000.0, true);

        Assert.assertEquals(room.getDescription(), "Luxury room with jacuzzi");
    }
}