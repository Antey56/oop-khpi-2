package ua.khpi.oop.lab08;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MultipleInterfaceTest {

    @Test
    public void luxuryRoomCanBeUsedAsReservable() {
        Reservable reservable = new LuxuryRoom("310", 2000.0, true);

        Assert.assertNotNull(reservable);
    }

    @Test
    public void luxuryRoomCanBeUsedAsCleanable() {
        Cleanable cleanable = new LuxuryRoom("310", 2000.0, true);

        Assert.assertNotNull(cleanable);
    }

    @Test
    public void familyRoomCanBeUsedAsBookable() {
        Bookable bookable = new FamilyRoom("205", 1200.0, 4);

        Assert.assertNotNull(bookable);
    }
}