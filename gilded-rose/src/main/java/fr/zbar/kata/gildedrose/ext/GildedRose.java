package fr.zbar.kata.gildedrose.ext;

import fr.zbar.kata.gildedrose.core.ItemUpdater;
import fr.zbar.kata.gildedrose.core.models.CoreItem;
import fr.zbar.kata.gildedrose.core.updater.quality.QualityUpdaterFactory;
import fr.zbar.kata.gildedrose.core.updater.sellin.SellInUpdater;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toUnmodifiableList;

public class GildedRose {

    private final ItemUpdater itemUpdater = new ItemUpdater(new QualityUpdaterFactory(), new SellInUpdater());
    private final ItemMapper itemMapper = new ItemMapper();

    public List<Item> updateQuality(List<Item> items) {
        List<CoreItem> updatedCoreItems = items.stream()
                .map(itemMapper::toCoreItem)
                .collect(Collectors.collectingAndThen(toUnmodifiableList(), itemUpdater::updateQuality));
        return updatedCoreItems.stream()
                .map(itemMapper::toItem)
                .toList();
    }
}
