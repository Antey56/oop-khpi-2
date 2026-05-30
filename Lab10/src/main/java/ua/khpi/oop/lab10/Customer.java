package ua.khpi.oop.lab10;

import java.util.Objects;

public class Customer {

    private final String name;
    private final String phone;

    public Customer(String name, String phone) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone cannot be empty");
        }

        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Customer customer)) {
            return false;
        }
        return Objects.equals(phone, customer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }
}
