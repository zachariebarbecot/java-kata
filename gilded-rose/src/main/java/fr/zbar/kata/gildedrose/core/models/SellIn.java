package fr.zbar.kata.gildedrose.core.models;

public record SellIn(Integer value) {

    public SellIn decreaseBy(Integer x) {
        return new SellIn(value - x);
    }
}
