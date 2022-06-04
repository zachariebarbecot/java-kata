package unit;

import fr.zbar.kata.leapyear.Year;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

class LeapYearTest {

    @ParameterizedTest
    @ValueSource(ints = {2000, 1600, 1200, 2400})
    void should_be_leap_year_when_divisible_by_400(Integer integerYear) {
        Year year = new Year(BigInteger.valueOf(integerYear));

        assertThat(Year.isLeap(year)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1700, 1800, 1900, 2100})
    void should_not_be_leap_year_when_divisible_by_100_but_not_400(Integer integerYear) {
        Year year = new Year(BigInteger.valueOf(integerYear));

        assertThat(Year.isLeap(year)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {2008, 2012, 2016})
    void should_be_leap_year_when_divisible_by_4_but_not_100(Integer integerYear) {
        Year year = new Year(BigInteger.valueOf(integerYear));

        assertThat(Year.isLeap(year)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {2017, 2018, 2019})
    void should_not_be_leap_year_when_not_divisible_by_4(Integer integerYear) {
        Year year = new Year(BigInteger.valueOf(integerYear));

        assertThat(Year.isLeap(year)).isFalse();
    }
}
