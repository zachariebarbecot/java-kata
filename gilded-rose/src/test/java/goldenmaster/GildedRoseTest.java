package goldenmaster;

import fr.zbar.kata.gildedrose.GildedRose;
import fr.zbar.kata.gildedrose.Item;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @Test
    void golden_master() {
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Conjured Mana Cake", 3, 6)
        };
        GildedRose app = new GildedRose(items);

        for (int i = 0; i < 7; i++) {
            app.updateQuality();
        }

        Approvals.verifyAll("", app.items);
    }

}