package ua.khpi.oop.lab07;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RoomInheritanceTest {

    @Test
    public void standardRoomIsRoom() {
        StandardRoom room = new StandardRoom("101", 800.0, true);

        Assert.assertTrue(room instanceof Room);
        Assert.assertEquals(room.getRoomType(), "standard");
    }

    @Test
    public void familyRoomStoresCapacity() {
        FamilyRoom room = new FamilyRoom("205", 1200.0, 4);

        Assert.assertEquals(room.getCapacity(), 4);
        Assert.assertEquals(room.getRoomType(), "family");
    }

    @Test
    public void luxuryRoomOverridesPrice() {
        LuxuryRoom room = new LuxuryRoom("310", 2000.0, true);

        Assert.assertEquals(room.getPricePerNight(), 3000.0);
    }

    @Test
    public void polymorphismWorksWithRoomReference() {
        Room room = new LuxuryRoom("310", 2000.0, true);

        Assert.assertEquals(room.getPricePerNight(), 3000.0);
        Assert.assertTrue(room.getDescription().contains("Luxury"));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void familyRoomWithInvalidCapacityThrowsException() {
        new FamilyRoom("205", 1200.0, 0);
    }
}