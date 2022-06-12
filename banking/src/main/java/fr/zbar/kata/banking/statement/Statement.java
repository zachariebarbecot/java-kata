package fr.zbar.kata.banking.statement;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public record Statement(List<StatementLine> values) {

    public static final Statement EMPTY = new Statement(Collections.emptyList());
    private static final String HEADER = """
            Date;Amount;Balance;
            """;

    public StatementLine last() {
        return values.stream()
                .reduce(StatementLine.EMPTY, latest());
    }

    public Statement concat(Statement other) {
        return new Statement(
                Stream.concat(
                                values.stream(),
                                other.values.stream()
                        )
                        .toList()
        );
    }

    public String print() {
        return HEADER + printLines();
    }

    private String printLines() {
        return values.stream()
                .filter(not(StatementLine.EMPTY::equals))
                .reduce(StringUtils.EMPTY, concatenate(), String::concat);
    }

    private BinaryOperator<StatementLine> latest() {
        return (s1, s2) -> s2;
    }

    private BiFunction<String, StatementLine, String> concatenate() {
        return (statementPrint, statementLine) -> statementPrint
                .concat(statementLine.print())
                .concat("\n");
    }
}
