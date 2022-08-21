package fr.zbar.kata.gildedrose.core.models;

import java.util.Objects;

public record Name(String value) {

    public Name {
        Objects.requireNonNull(value);
    }

    public Boolean isEqualTo(String name) {
        return value.equals(name);
    }

    public Boolean startsWith(String name) {
        return value.startsWith(name);
    }
}
