package fr.zbar.kata.banking.statement;

import fr.zbar.kata.banking.transaction.Amount;

import java.util.Objects;

public record Balance(Integer value) {

    public static final Balance ZERO = new Balance(0);

    public Balance {
        Objects.requireNonNull(value);
    }

    public Balance add(Amount amount) {
        return new Balance(value + amount.value());
    }
}
