package fr.zbar.kata.gildedrose.core.updater.quality;

import fr.zbar.kata.gildedrose.core.models.CoreItem;

public final class BackstagePassQualityUpdater implements QualityUpdater {

    @Override
    public CoreItem updateQualityFor(CoreItem coreItem) {
        if (coreItem.sellIn().value() <= ZERO_DAY) {
            return coreItem.decreaseQualityBy(coreItem.quality().value());
        }
        if (coreItem.sellIn().value() < FIVE_DAYS) {
            return coreItem.increaseQualityBy(TRIPLE_QUALITY_STEP);
        }
        if (coreItem.sellIn().value() < TEN_DAYS) {
            return coreItem.increaseQualityBy(DOUBLE_QUALITY_STEP);
        }
        return coreItem.increaseQualityBy(DEFAULT_QUALITY_STEP);
    }
}
