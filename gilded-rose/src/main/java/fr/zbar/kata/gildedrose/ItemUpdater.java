package fr.zbar.kata.gildedrose;

public class ItemUpdater {

    static void updateQuality(Item item) {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }

        item.sellIn = item.sellIn - 1;

        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            updateQualityForBackstagePasses(item);
        } else if (item.name.equals("Aged Brie")) {
            updateQualityForAgedBrie(item);
        } else {
            updateQualityForCommonItem(item);
        }
    }

    private static void updateQualityForBackstagePasses(Item item) {
        if (item.sellIn < 10) {
            increaseQuality(item);
        }
        if (item.sellIn < 5) {
            increaseQuality(item);
        }
        increaseQuality(item);
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private static void updateQualityForAgedBrie(Item item) {
        increaseQuality(item);
        if (item.sellIn < 0) {
            increaseQuality(item);
        }
    }

    private static void updateQualityForCommonItem(Item item) {
        decreaseQuality(item);
        if (item.sellIn < 0) {
            decreaseQuality(item);
        }
    }

    private static void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private static void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}
