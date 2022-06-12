package fr.zbar.kata.banking.transaction;

import fr.zbar.kata.banking.statement.Balance;
import fr.zbar.kata.banking.statement.StatementLine;

import java.time.LocalDate;

public record WithdrawTransaction(LocalDate date, Amount amount) implements Transaction {

    @Override
    public StatementLine toStatementLine(Balance balance) {
        Amount withdrawAmount = amount.negate();
        return new StatementLine(date, withdrawAmount, balance.add(withdrawAmount));
    }
}
