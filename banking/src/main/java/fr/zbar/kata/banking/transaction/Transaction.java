package fr.zbar.kata.banking.transaction;

import fr.zbar.kata.banking.statement.Balance;
import fr.zbar.kata.banking.statement.StatementLine;

public sealed interface Transaction
        permits DepositTransaction, WithdrawTransaction {

    StatementLine toStatementLine(Balance balance);
}
