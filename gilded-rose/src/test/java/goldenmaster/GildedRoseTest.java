package goldenmaster;

import fr.zbar.kata.gildedrose.GildedRose;
import fr.zbar.kata.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    @Test
    void foo() {
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

        app.updateQuality();

        assertThat(app.items)
                .extracting("name")
                .containsExactly(
                        "+5 Dexterity Vest",
                        "Aged Brie",
                        "Elixir of the Mongoose",
                        "Sulfuras, Hand of Ragnaros",
                        "Sulfuras, Hand of Ragnaros",
                        "Backstage passes to a TAFKAL80ETC concert",
                        "Backstage passes to a TAFKAL80ETC concert",
                        "Backstage passes to a TAFKAL80ETC concert",
                        "Conjured Mana Cake"
                );
        assertThat(app.items)
                .extracting("sellIn")
                .containsExactly(9, 1, 4, 0, -1, 14, 9, 4, 2);
        assertThat(app.items)
                .extracting("quality")
                .containsExactly(19, 1, 6, 80, 80, 21, 50, 50, 5);
    }

}