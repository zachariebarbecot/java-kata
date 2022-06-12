package fr.zbar.kata.banking.transaction;

import fr.zbar.kata.banking.statement.Balance;
import fr.zbar.kata.banking.statement.StatementLine;

import java.time.LocalDate;

public record DepositTransaction(LocalDate date, Amount amount) implements Transaction {

    @Override
    public StatementLine toStatementLine(Balance balance) {
        return new StatementLine(date, amount, balance.add(amount));
    }
}
