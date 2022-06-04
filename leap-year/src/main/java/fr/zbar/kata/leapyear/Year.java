package fr.zbar.kata.leapyear;

import java.math.BigInteger;

public record Year(BigInteger value) {

    private static final BigInteger HUNDRED = BigInteger.valueOf(100);
    private static final BigInteger FOUR = BigInteger.valueOf(4);
    private static final BigInteger FOUR_HUNDRED = HUNDRED.multiply(FOUR);

    public static Boolean isLeap(Year year) {
        return year.isDivisibleBy(FOUR_HUNDRED) ||
                (year.isDivisibleBy(FOUR) && !year.isDivisibleBy(HUNDRED));
    }

    private Boolean isDivisibleBy(BigInteger divisor) {
        return value.mod(divisor)
                .equals(BigInteger.ZERO);
    }
}
