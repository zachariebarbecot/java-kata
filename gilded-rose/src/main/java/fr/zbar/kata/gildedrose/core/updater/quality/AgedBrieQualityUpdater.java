package fr.zbar.kata.gildedrose.core.updater.quality;

import fr.zbar.kata.gildedrose.core.models.CoreItem;

public final class AgedBrieQualityUpdater implements QualityUpdater {

    @Override
    public CoreItem updateQualityFor(CoreItem coreItem) {
        return coreItem.increaseQualityBy(DEFAULT_QUALITY_STEP);
    }

}
