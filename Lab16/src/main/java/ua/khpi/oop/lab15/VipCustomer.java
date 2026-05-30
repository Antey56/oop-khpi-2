package ua.khpi.oop.lab15;

public class VipCustomer extends Customer {
    private final int bonusLevel;

    public VipCustomer(String customerId, String fullName, String phone, int bonusLevel) {
        super(customerId, fullName, phone);
        if (bonusLevel < 1) {
            throw new IllegalArgumentException("Bonus level must be positive");
        }
        this.bonusLevel = bonusLevel;
    }

    public int getBonusLevel() {
        return bonusLevel;
    }
}
