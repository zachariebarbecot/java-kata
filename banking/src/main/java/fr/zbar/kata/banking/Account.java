package fr.zbar.kata.banking;

import fr.zbar.kata.banking.ext.TransactionRepository;
import fr.zbar.kata.banking.transaction.Amount;
import fr.zbar.kata.banking.transaction.DepositTransaction;
import fr.zbar.kata.banking.transaction.Transaction;
import fr.zbar.kata.banking.transaction.WithdrawTransaction;

import java.time.Clock;
import java.time.LocalDate;

public final class Account {

    private final Clock clock;
    private final TransactionRepository transactionRepository;

    public Account(Clock clock, TransactionRepository transactionRepository) {
        this.clock = clock;
        this.transactionRepository = transactionRepository;
    }

    public void deposit(Integer amount) {
        Transaction transaction = new DepositTransaction(LocalDate.now(clock), new Amount(amount));
        transactionRepository.save(transaction);
    }

    public void withdraw(Integer amount) {
        Transaction transaction = new WithdrawTransaction(LocalDate.now(clock), new Amount(amount));
        transactionRepository.save(transaction);
    }

    public String printStatement() {
        return transactionRepository.all()
                .toStatement()
                .print();
    }
}
