package ua.khpi.oop.lab08;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InterfaceImplementationTest {

    @Test
    public void standardRoomImplementsReservable() {
        StandardRoom room = new StandardRoom("101", 800.0, true);

        Assert.assertTrue(room instanceof Reservable);
    }

    @Test
    public void familyRoomImplementsReservableAndBookable() {
        FamilyRoom room = new FamilyRoom("205", 1200.0, 4);

        Assert.assertTrue(room instanceof Reservable);
        Assert.assertTrue(room instanceof Bookable);
    }

    @Test
    public void luxuryRoomImplementsReservableAndCleanable() {
        LuxuryRoom room = new LuxuryRoom("310", 2000.0, true);

        Assert.assertTrue(room instanceof Reservable);
        Assert.assertTrue(room instanceof Cleanable);
    }
}