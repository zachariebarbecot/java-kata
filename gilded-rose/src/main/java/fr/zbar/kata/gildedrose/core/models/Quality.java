package fr.zbar.kata.gildedrose.core.models;

import static java.lang.Math.max;
import static org.apache.commons.lang3.math.NumberUtils.min;

public record Quality(Integer value) {

    private final static Integer MIN_QUALITY_ALLOWED = 0;
    private final static Integer MAX_QUALITY_ALLOWED = 50;

    public Quality decreaseBy(Integer x) {
        return new Quality(
                max(value - x, MIN_QUALITY_ALLOWED)
        );
    }

    public Quality increaseBy(Integer x) {
        return new Quality(
                min(value + x, MAX_QUALITY_ALLOWED)
        );
    }
}
