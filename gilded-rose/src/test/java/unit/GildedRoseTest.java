package unit;

import fr.zbar.kata.gildedrose.ext.GildedRose;
import fr.zbar.kata.gildedrose.ext.Item;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class GildedRoseTest {

    private final GildedRose app = new GildedRose();

    @Nested
    class SellInTest {

        @Test
        void should_not_update_sell_in_when_item_is_the_legenday_sulfuras() {
            List<Item> input = List.of(new Item("Sulfuras, Hand of Ragnaros", 0, 80));

            List<Item> result = app.updateQuality(input);

            assertThat(result)
                    .hasSize(1)
                    .extracting(item -> item.name, item -> item.sellIn)
                    .containsExactly(tuple("Sulfuras, Hand of Ragnaros", 0));
        }

        @ParameterizedTest
        @CsvSource({
                "Backstage passes to a TAFKAL80ETC concert,5,4",
                "Aged Brie,-5,-6",
                "+5 Dexterity Vest,0,-1",
                "Elixir of the Mongoose,49,48"
        })
        void should_decrease_sell_in_for_all_items_expect_legendary_sulfuras(String name, Integer initialSellIn, Integer expectedSellIn) {
            List<Item> input = List.of(new Item(name, initialSellIn, 10));

            List<Item> result = app.updateQuality(input);

            assertThat(result)
                    .hasSize(1)
                    .extracting(item -> item.name, item -> item.sellIn)
                    .containsExactly(tuple(name, expectedSellIn));
        }

        @ParameterizedTest
        @CsvSource(value = {
                "Conjured Sulfuras, Hand of Ragnaros;80;78",
                "Conjured Backstage passes to a TAFKAL80ETC concert;5;3",
                "Conjured Aged Brie;-5;-7",
                "Conjured +5 Dexterity Vest;0;-2",
                "Conjured Elixir of the Mongoose;49;47",
                "Conjured Mana Cake;6;4",
        }, delimiter = ';')
        void should_decrease_sell_in_two_times_for_all_conjured_items(String name, Integer initialSellIn, Integer expectedSellIn) {
            List<Item> input = List.of(new Item(name, initialSellIn, 10));

            List<Item> result = app.updateQuality(input);

            assertThat(result)
                    .hasSize(1)
                    .extracting(item -> item.name, item -> item.sellIn)
                    .containsExactly(tuple(name, expectedSellIn));
        }
    }

    @Nested
    class Quality {

        @Test
        void should_not_update_quality_when_item_is_the_legendary_sulfuras() {
            List<Item> input = List.of(new Item("Sulfuras, Hand of Ragnaros", 0, 80));

            List<Item> result = app.updateQuality(input);

            assertThat(result)
                    .hasSize(1)
                    .extracting(item -> item.name, item -> item.quality)
                    .containsExactly(tuple("Sulfuras, Hand of Ragnaros", 80));
        }

        @ParameterizedTest
        @CsvSource({
                "11,20,21",
                "15,10,11",
                "20,45,46"
        })
        void should_increase_quality_when_item_is_backstage_pass_with_sell_in_greater_than_ten(Integer sellIn, Integer initialQuality, Integer expectedQuality) {
            List<Item> input = List.of(new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, initialQuality));

            List<Item> result = app.updateQuality(input);

            assertThat(result)
                    .hasSize(1)
                    .extracting(item -> item.name, item -> item.quality)
                    .containsExactly(tuple("Backstage passes to a TAFKAL80ETC concert", expectedQuality));
        }

        @ParameterizedTest
        @CsvSource({
                "10,20,22",
                "8,10,12",
                "6,45,47"
        })
        void should_increase_quality_two_times_when_item_is_backstage_pass_with_sell_in_less_than_ten(Integer sellIn, Integer initialQuality, Integer expectedQuality) {
            List<Item> input = List.of(new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, initialQuality));

            List<Item> result = app.updateQuality(input);

            assertThat(result)
                    .hasSize(1)
                    .extracting(item -> item.name, item -> item.quality)
                    .containsExactly(tuple("Backstage passes to a TAFKAL80ETC concert", expectedQuality));
        }

        @ParameterizedTest
        @CsvSource({
                "5,20,23",
                "3,10,13",
                "2,45,48"
        })
        void should_increase_quality_three_times_when_item_is_backstage_pass_with_sell_in_less_than_five(Integer sellIn, Integer initialQuality, Integer expectedQuality) {
            List<Item> input = List.of(new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, initialQuality));

            List<Item> result = app.updateQuality(input);

            assertThat(result)
                    .hasSize(1)
                    .extracting(item -> item.name, item -> item.quality)
                    .containsExactly(tuple("Backstage passes to a TAFKAL80ETC concert", expectedQuality));
        }

        @Test
        void should_decrease_quality_to_zero_when_item_is_backstage_pass_sell_in_is_expired() {
            List<Item> input = List.of(new Item("Backstage passes to a TAFKAL80ETC concert", 1, 10));

            List<Item> result = app.updateQuality(input);

            assertThat(result)
                    .hasSize(1)
                    .extracting(item -> item.name, item -> item.quality)
                    .containsExactly(tuple("Backstage passes to a TAFKAL80ETC concert", 0));
        }

        @Test
        void should_increase_quality_when_aged_brie_item() {
            List<Item> input = List.of(new Item("Aged Brie", 10, 12));

            List<Item> result = app.updateQuality(input);

            assertThat(result)
                    .hasSize(1)
                    .extracting(item -> item.name, item -> item.quality)
                    .containsExactly(tuple("Aged Brie", 13));
        }

        @Test
        void should_decrease_quality_when_common_item() {
            List<Item> input = List.of(new Item("+5 Dexterity Vest", 2, 5));

            List<Item> result = app.updateQuality(input);

            assertThat(result)
                    .hasSize(1)
                    .extracting(item -> item.name, item -> item.quality)
                    .containsExactly(tuple("+5 Dexterity Vest", 4));
        }

        @Test
        void should_decrease_quality_two_times_when_common_item_sell_in_is_expired() {
            List<Item> input = List.of(new Item("Elixir of the Mongoose", 0, 5));

            List<Item> result = app.updateQuality(input);

            assertThat(result)
                    .hasSize(1)
                    .extracting(item -> item.name, item -> item.quality)
                    .containsExactly(tuple("Elixir of the Mongoose", 3));
        }

        @ParameterizedTest
        @CsvSource({
                "Backstage passes to a TAFKAL80ETC concert,2,48",
                "Backstage passes to a TAFKAL80ETC concert,7,49",
                "Backstage passes to a TAFKAL80ETC concert,15,50",
                "Aged Brie,-15,49",
                "Aged Brie,12,50",
        })
        void should_increase_quality_until_fifty(String name, Integer sellIn, Integer quality) {
            List<Item> input = List.of(new Item(name, sellIn, quality));

            List<Item> result = app.updateQuality(input);

            assertThat(result)
                    .hasSize(1)
                    .extracting(item -> item.name, item -> item.quality)
                    .containsExactly(tuple(name, 50));
        }

        @ParameterizedTest
        @CsvSource({
                "+5 Dexterity Vest,-2,1",
                "Elixir of the Mongoose,5,0",
        })
        void should_decrease_quality_until_zero(String name, Integer sellIn, Integer quality) {
            List<Item> input = List.of(new Item(name, sellIn, quality));

            List<Item> result = app.updateQuality(input);

            assertThat(result)
                    .hasSize(1)
                    .extracting(item -> item.name, item -> item.quality)
                    .containsExactly(tuple(name, 0));
        }

        @ParameterizedTest
        @CsvSource(value = {
                "Conjured Sulfuras, Hand of Ragnaros;50;48",
                "Conjured Backstage passes to a TAFKAL80ETC concert;5;3",
                "Conjured Aged Brie;32;30",
                "Conjured +5 Dexterity Vest;3;1",
                "Conjured Elixir of the Mongoose;39;37",
                "Conjured Mana Cake;6;4",
        }, delimiter = ';')
        void should_decrease_quality_two_times_for_all_conjured_items(String name, Integer quality, Integer expectedQuality) {
            List<Item> input = List.of(new Item(name, 10, quality));

            List<Item> result = app.updateQuality(input);

            assertThat(result)
                    .hasSize(1)
                    .extracting(item -> item.name, item -> item.quality)
                    .containsExactly(tuple(name, expectedQuality));
        }
    }
}