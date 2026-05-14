package ua.khpi.oop.lab08;

import java.util.Objects;

public class Guest {
    private final String guestId;
    private String fullName;
    private String phone;

    public Guest(String guestId, String fullName, String phone) {
        if (guestId == null || guestId.isBlank()) {
            throw new IllegalArgumentException("Guest id must not be blank");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name must not be blank");
        }

        this.guestId = guestId;
        this.fullName = fullName;
        this.phone = phone;
    }

    public Guest(String guestId, String fullName) {
        this(guestId, fullName, "-");
    }

    public String getGuestId() {
        return guestId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public boolean hasPhone() {
        return phone != null && !phone.equals("-");
    }

    @Override
    public String toString() {
        return "Guest{guestId='" + guestId + "', fullName='" + fullName +
                "', phone='" + phone + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Guest other))
            return false;
        return Objects.equals(guestId, other.guestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guestId);
    }
}