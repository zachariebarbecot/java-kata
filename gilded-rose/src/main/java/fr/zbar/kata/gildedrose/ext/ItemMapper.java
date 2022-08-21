package fr.zbar.kata.gildedrose.ext;

import fr.zbar.kata.gildedrose.core.models.CoreItem;
import fr.zbar.kata.gildedrose.core.models.Name;
import fr.zbar.kata.gildedrose.core.models.Quality;
import fr.zbar.kata.gildedrose.core.models.SellIn;

public class ItemMapper {

    public CoreItem toCoreItem(Item item) {
        if (item == null) {
            return null;
        }
        return new CoreItem(
                toCoreName(item.name),
                toCoreSellIn(item.sellIn),
                toCoreQuality(item.quality)
        );
    }

    private Name toCoreName(String name) {
        if (name == null) {
            return null;
        }
        return new Name(name);
    }

    private SellIn toCoreSellIn(Integer sellIn) {
        if (sellIn == null) {
            return null;
        }
        return new SellIn(sellIn);
    }

    private Quality toCoreQuality(Integer quality) {
        if (quality == null) {
            return null;
        }
        return new Quality(quality);
    }

    public Item toItem(CoreItem coreItem) {
        if (coreItem == null) {
            return null;
        }
        return new Item(
                toItemName(coreItem.name()),
                toItemSellIn(coreItem.sellIn()),
                toItemQuality(coreItem.quality())
        );
    }

    private String toItemName(Name name) {
        if (name == null) {
            return null;
        }
        return name.value();
    }

    private Integer toItemSellIn(SellIn sellIn) {
        if (sellIn == null) {
            return null;
        }
        return sellIn.value();
    }

    private Integer toItemQuality(Quality quality) {
        if (quality == null) {
            return null;
        }
        return quality.value();
    }
}
