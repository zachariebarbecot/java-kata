package fr.zbar.kata.leapyear;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.Predicate;

public record Year(BigInteger value) {

    private static final BigInteger HUNDRED = BigInteger.valueOf(100);
    private static final BigInteger FOUR = BigInteger.valueOf(4);
    private static final BigInteger FOUR_HUNDRED = HUNDRED.multiply(FOUR);

    public static Boolean isLeap(Year year) {
        return Arrays.stream(LeapYearRules.values())
                .anyMatch(rule -> rule.verify(year));
    }

    private Boolean isDivisibleBy(BigInteger divisor) {
        return value.mod(divisor)
                .equals(BigInteger.ZERO);
    }

    private enum LeapYearRules {
        DIVISIBLE_BY_400(year -> year.isDivisibleBy(FOUR_HUNDRED)),
        DIVISIBLE_BY_4_AND_NOT_BY_HUNDRED(year -> year.isDivisibleBy(FOUR) && !year.isDivisibleBy(HUNDRED));

        private final Predicate<Year> rule;

        LeapYearRules(Predicate<Year> rule) {
            this.rule = rule;
        }

        public Boolean verify(Year year) {
            return rule.test(year);
        }
    }
}
