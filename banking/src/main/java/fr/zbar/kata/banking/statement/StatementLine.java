package fr.zbar.kata.banking.statement;

import fr.zbar.kata.banking.transaction.Amount;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record StatementLine(LocalDate date, Amount amount, Balance balance) {

    public static final StatementLine EMPTY = new StatementLine(null, Amount.ZERO, Balance.ZERO);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = ";";

    public String print() {
        return date.format(DATE_TIME_FORMATTER) + SEPARATOR
                + amount.withSign() + SEPARATOR
                + balance.value() + SEPARATOR;
    }
}
