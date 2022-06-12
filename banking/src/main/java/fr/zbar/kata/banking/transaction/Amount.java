package fr.zbar.kata.banking.transaction;

import java.util.Objects;

public record Amount(Integer value) {

    public static final Amount ZERO = new Amount(0);

    public Amount {
        Objects.requireNonNull(value);
    }

    public Amount add(Amount other) {
        return new Amount(value + other.value);
    }

    public Amount negate() {
        return new Amount(-value);
    }

    public String withSign() {
        if (isPositive()) {
            return "+" + value;
        }
        return value.toString();
    }

    private Boolean isPositive() {
        return value >= 0;
    }
}
