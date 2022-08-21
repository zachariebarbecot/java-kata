package fr.zbar.kata.gildedrose.core;

import fr.zbar.kata.gildedrose.core.models.CoreItem;
import fr.zbar.kata.gildedrose.core.updater.quality.QualityUpdater;
import fr.zbar.kata.gildedrose.core.updater.quality.QualityUpdaterFactory;
import fr.zbar.kata.gildedrose.core.updater.sellin.SellInUpdater;

import java.util.List;

public final class ItemUpdater {

    private final QualityUpdaterFactory qualityUpdaterFactory;
    private final SellInUpdater sellInUpdater;

    public ItemUpdater(QualityUpdaterFactory qualityUpdaterFactory, SellInUpdater sellInUpdater) {
        this.qualityUpdaterFactory = qualityUpdaterFactory;
        this.sellInUpdater = sellInUpdater;
    }

    public List<CoreItem> updateQuality(List<CoreItem> coreItems) {
        return coreItems.stream()
                .map(this::updateSellInFor)
                .map(this::updateQualityFor)
                .toList();
    }

    private CoreItem updateSellInFor(CoreItem coreItem) {
        return sellInUpdater.updateSellInFor(coreItem);
    }

    private CoreItem updateQualityFor(CoreItem coreItem) {
        QualityUpdater qualitySelector = qualityUpdaterFactory.selectorFor(coreItem);
        return qualitySelector.updateQualityFor(coreItem);
    }
}