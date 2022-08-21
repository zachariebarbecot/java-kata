package fr.zbar.kata.gildedrose.core.updater.quality;

import fr.zbar.kata.gildedrose.core.models.CoreItem;

public final class ConjuredQualityUpdater implements QualityUpdater {

    @Override
    public CoreItem updateQualityFor(CoreItem coreItem) {
        return coreItem.decreaseQualityBy(DOUBLE_QUALITY_STEP);
    }
}
