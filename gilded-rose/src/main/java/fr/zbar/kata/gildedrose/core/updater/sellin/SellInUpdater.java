package fr.zbar.kata.gildedrose.core.updater.sellin;

import fr.zbar.kata.gildedrose.core.models.CoreItem;

import static fr.zbar.kata.gildedrose.core.updater.quality.QualityUpdaterFactory.CONJURED_ITEM_NAME;
import static fr.zbar.kata.gildedrose.core.updater.quality.QualityUpdaterFactory.SULFURAS_ITEM_NAME;

public class SellInUpdater {

    Integer DEFAULT_STEP_IN_STEP = 1;
    Integer DOUBLE_STEP_IN_STEP = 2;

    public CoreItem updateSellInFor(CoreItem coreItem) {
        if (coreItem.isNameStartWith(CONJURED_ITEM_NAME)) {
            return coreItem.decreaseSellInBy(DOUBLE_STEP_IN_STEP);
        }
        if (coreItem.isNameEqual(SULFURAS_ITEM_NAME)) {
            return coreItem;
        }
        return coreItem.decreaseSellInBy(DEFAULT_STEP_IN_STEP);
    }
}
