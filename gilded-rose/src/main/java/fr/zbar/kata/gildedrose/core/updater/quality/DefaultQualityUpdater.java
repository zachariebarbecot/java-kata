package fr.zbar.kata.gildedrose.core.updater.quality;

import fr.zbar.kata.gildedrose.core.models.CoreItem;

public final class DefaultQualityUpdater implements QualityUpdater {

    @Override
    public CoreItem updateQualityFor(CoreItem coreItem) {
        if (coreItem.sellIn().value() <= ZERO_DAY) {
            return coreItem.decreaseQualityBy(DOUBLE_QUALITY_STEP);
        }
        return coreItem.decreaseQualityBy(DEFAULT_QUALITY_STEP);
    }
}
