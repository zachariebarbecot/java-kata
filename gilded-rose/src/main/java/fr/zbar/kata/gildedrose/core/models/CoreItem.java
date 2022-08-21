package fr.zbar.kata.gildedrose.core.models;

public record CoreItem(Name name, SellIn sellIn, Quality quality) {

    public Boolean isNameEqual(String itemName) {
        return name.isEqualTo(itemName);
    }

    public Boolean isNameStartWith(String itemName) {
        return name.startsWith(itemName);
    }

    public CoreItem increaseQualityBy(Integer x) {
        return new CoreItem(name, sellIn, quality.increaseBy(x));
    }

    public CoreItem decreaseQualityBy(Integer x) {
        return new CoreItem(name, sellIn, quality.decreaseBy(x));
    }

    public CoreItem decreaseSellInBy(Integer x) {
        return new CoreItem(name, sellIn.decreaseBy(x), quality);
    }
}