package goldenmaster;

import fr.zbar.kata.gildedrose.GildedRose;
import fr.zbar.kata.gildedrose.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class GildedRoseTest {

    @ParameterizedTest
    @CsvSource({
            "0,80",
            "-1,80"
    })
    void should_not_update_quality_when_item_is_the_legenday_sulfuras(Integer sellIn, Integer quality) {
        Item[] items = new Item[]{
                new Item("Sulfuras, Hand of Ragnaros", sellIn, quality)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(items)
                .hasSize(1)
                .extracting("name", "sellIn", "quality")
                .containsExactly(tuple("Sulfuras, Hand of Ragnaros", sellIn, quality));
    }

    @ParameterizedTest
    @CsvSource({
            "11,20",
            "15,10",
            "20,45"
    })
    void should_increase_quality_when_item_is_backstage_passes_with_sell_in_greater_than_10(Integer sellIn, Integer quality) {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        Integer expectedSellIn = sellIn - 1;
        Integer expectedQuality = quality + 1;
        assertThat(items)
                .hasSize(1)
                .extracting("name", "sellIn", "quality")
                .containsExactly(tuple("Backstage passes to a TAFKAL80ETC concert", expectedSellIn, expectedQuality));
    }

    @ParameterizedTest
    @CsvSource({
            "10,20",
            "8,10",
            "6,45"
    })
    void should_increase_quality_two_times_when_item_is_backstage_passes_with_sell_in_less_than_10(Integer sellIn, Integer quality) {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        Integer expectedSellIn = sellIn - 1;
        Integer expectedQuality = quality + 2;
        assertThat(items)
                .hasSize(1)
                .extracting("name", "sellIn", "quality")
                .containsExactly(tuple("Backstage passes to a TAFKAL80ETC concert", expectedSellIn, expectedQuality));
    }

    @ParameterizedTest
    @CsvSource({
            "5,20",
            "3,10",
            "2,45"
    })
    void should_increase_quality_three_times_when_item_is_backstage_passes_with_sell_in_less_than_5(Integer sellIn, Integer quality) {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        Integer expectedSellIn = sellIn - 1;
        Integer expectedQuality = quality + 3;
        assertThat(items)
                .hasSize(1)
                .extracting("name", "sellIn", "quality")
                .containsExactly(tuple("Backstage passes to a TAFKAL80ETC concert", expectedSellIn, expectedQuality));
    }

    @Test
    void should_update_quality_with_zero__when_item_is_backstage_passes_sell_in_is_expired() {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(items)
                .hasSize(1)
                .extracting("name", "sellIn", "quality")
                .containsExactly(tuple("Backstage passes to a TAFKAL80ETC concert", -1, 0));
    }

    @Test
    void should_increase_quality_when_aged_brie_item() {
        Item[] items = new Item[]{
                new Item("Aged Brie", 2, 0)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(items)
                .hasSize(1)
                .extracting("name", "sellIn", "quality")
                .containsExactly(tuple("Aged Brie", 0, 1));
    }

    @Test
    void should_increase_quality_two_times_when_aged_brie_item_sell_in_is_expired() {
        Item[] items = new Item[]{
                new Item("Aged Brie", 0, 0)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(items)
                .hasSize(1)
                .extracting("name", "sellIn", "quality")
                .containsExactly(tuple("Aged Brie", -1, 2));
    }

    @Test
    void should_decrease_quality_when_common_item() {
        Item[] items = new Item[]{
                new Item("Thing", 2, 5)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(items)
                .hasSize(1)
                .extracting("name", "sellIn", "quality")
                .containsExactly(tuple("Thing", 1, 4));
    }

    @Test
    void should_decrease_quality_two_times_when_common_item_sell_in_is_expired() {
        Item[] items = new Item[]{
                new Item("Thing", 0, 5)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(items)
                .hasSize(1)
                .extracting("name", "sellIn", "quality")
                .containsExactly(tuple("Thing", -1, 3));
    }
}