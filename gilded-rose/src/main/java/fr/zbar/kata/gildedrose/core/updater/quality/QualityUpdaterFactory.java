package fr.zbar.kata.gildedrose.core.updater.quality;

import fr.zbar.kata.gildedrose.core.models.CoreItem;
import fr.zbar.kata.gildedrose.core.models.Name;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class QualityUpdaterFactory {
    public static final String SULFURAS_ITEM_NAME = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASS_ITEM_NAME = "Backstage passes to a TAFKAL80ETC concert";
    public static final String AGED_BRIE_ITEM_NAME = "Aged Brie";
    public static final String CONJURED_ITEM_NAME = "Conjured";

    private enum ItemQualityUpdater {
        SULFURAS(itemName -> itemName.isEqualTo(SULFURAS_ITEM_NAME), SulfurasQualityUpdater::new),
        BACKSTAGE_PASSES(itemName -> itemName.isEqualTo(BACKSTAGE_PASS_ITEM_NAME), BackstagePassQualityUpdater::new),
        AGED_BRIE(itemName -> itemName.isEqualTo(AGED_BRIE_ITEM_NAME), AgedBrieQualityUpdater::new),
        CONJURED_(itemName-> itemName.startsWith(CONJURED_ITEM_NAME), ConjuredQualityUpdater::new);

        private final Predicate<Name> nameVerification;
        private final Supplier<QualityUpdater> selector;

        ItemQualityUpdater(Predicate<Name> nameVerification, Supplier<QualityUpdater> selector) {
            this.nameVerification = nameVerification;
            this.selector = selector;
        }

        public static QualityUpdater selectorFor(CoreItem coreItem) {
            return Arrays.stream(ItemQualityUpdater.values())
                    .filter(itemQualityUpdater -> itemQualityUpdater.nameVerification.test(coreItem.name()))
                    .map(itemQualityUpdater -> itemQualityUpdater.selector.get())
                    .findFirst()
                    .orElseGet(DefaultQualityUpdater::new);
        }
    }

    public QualityUpdater selectorFor(CoreItem coreItem) {
        return ItemQualityUpdater.selectorFor(coreItem);
    }
}
