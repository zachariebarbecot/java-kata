package fr.zbar.kata.dictionaryreplacer;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public final class Dictionary {

    public record Entry(String key, String value) {
    }

    private final List<Entry> values;

    public Dictionary(Entry... entries) {
        this.values = List.of(entries);
    }

    public static String replace(String input, Dictionary dictionary) {
        Objects.requireNonNull(input, "input is null");
        Objects.requireNonNull(dictionary, "dictionary is null");
        return dictionary.stream()
                .reduce(input, replacer(), last());
    }

    private static BiFunction<String, Entry, String> replacer() {
        return (s, entry) -> s.replace(String.format("$%s$", entry.key()), entry.value());
    }

    private static BinaryOperator<String> last() {
        return (first, second) -> second;
    }

    public Stream<Entry> stream() {
        return values.stream();
    }
}
