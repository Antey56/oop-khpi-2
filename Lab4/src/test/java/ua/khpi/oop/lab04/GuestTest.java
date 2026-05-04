package ua.khpi.oop.lab04;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GuestTest {

    @Test(groups = { "smoke", "domain" })
    public void guestWithPhoneHasPhone() {
        Guest guest = new Guest("G-01", "Olena Kovalenko", "+380501234567");

        Assert.assertTrue(guest.hasPhone());
    }

    @Test(groups = { "domain" })
    public void guestWithoutPhoneDoesNotHavePhone() {
        Guest guest = new Guest("G-02", "Mykhailo Bondar");

        Assert.assertFalse(guest.hasPhone());
    }

    @Test(groups = { "domain" })
    public void guestsWithSameIdAreEqual() {
        Guest first = new Guest("G-01", "Olena Kovalenko");
        Guest second = new Guest("G-01", "Olena K.");

        Assert.assertEquals(first, second);
        Assert.assertEquals(first.hashCode(), second.hashCode());
    }

    @Test(groups = { "negative" }, expectedExceptions = IllegalArgumentException.class)
    public void blankGuestIdThrowsException() {
        new Guest("", "Olena Kovalenko");
    }

    @DataProvider(name = "validGuests")
    public Object[][] validGuests() {
        return new Object[][] {
                { "G-01", "Olena Kovalenko", "+380501234567" },
                { "G-02", "Mykhailo Bondar", "-" },
                { "G-03", "Iryna Shevchenko", "+380671112233" }
        };
    }

    @Test(dataProvider = "validGuests", groups = { "domain" })
    public void guestDataProviderCreatesGuests(String id, String name, String phone) {
        Guest guest = new Guest(id, name, phone);

        Assert.assertEquals(guest.getGuestId(), id);
        Assert.assertEquals(guest.getFullName(), name);
        Assert.assertEquals(guest.getPhone(), phone);
    }
}