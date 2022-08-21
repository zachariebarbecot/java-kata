package fr.zbar.kata.gildedrose.core.updater.quality;

import fr.zbar.kata.gildedrose.core.models.CoreItem;

public sealed interface QualityUpdater
        permits AgedBrieQualityUpdater, BackstagePassQualityUpdater, ConjuredQualityUpdater, DefaultQualityUpdater, SulfurasQualityUpdater {

    Integer ZERO_DAY = 0;
    Integer FIVE_DAYS = 5;
    Integer TEN_DAYS = 10;
    Integer DEFAULT_QUALITY_STEP = 1;
    Integer DOUBLE_QUALITY_STEP = 2;
    Integer TRIPLE_QUALITY_STEP = 3;

    CoreItem updateQualityFor(CoreItem coreItem);
}
