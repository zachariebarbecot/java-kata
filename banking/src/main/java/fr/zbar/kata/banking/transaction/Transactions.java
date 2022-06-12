package fr.zbar.kata.banking.transaction;

import fr.zbar.kata.banking.statement.Statement;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public record Transactions(List<Transaction> values) {

    public static final Transactions EMPTY = new Transactions(Collections.emptyList());

    public Statement toStatement() {
        return values.stream()
                .reduce(Statement.EMPTY, statementBuilder(), Statement::concat);
    }

    private BiFunction<Statement, Transaction, Statement> statementBuilder() {
        return (statement, transaction) -> new Statement(
                Stream.concat(
                                statement.values().stream(),
                                Stream.of(transaction.toStatementLine(statement.last().balance())))
                        .toList()
        );
    }
}
