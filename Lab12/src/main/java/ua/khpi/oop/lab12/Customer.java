package ua.khpi.oop.lab12;

import java.util.Objects;

public class Customer {
    private final String customerId;
    private final String fullName;
    private final String phone;

    public Customer(String customerId, String fullName, String phone) {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("Customer id must not be blank");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name must not be blank");
        }
        this.customerId = customerId;
        this.fullName = fullName;
        this.phone = phone == null || phone.isBlank() ? "-" : phone;
    }

    public Customer(String customerId, String fullName) {
        this(customerId, fullName, "-");
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Customer{customerId='" + customerId + "', fullName='" + fullName +
                "', phone='" + phone + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Customer other)) {
            return false;
        }
        return Objects.equals(customerId, other.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}
