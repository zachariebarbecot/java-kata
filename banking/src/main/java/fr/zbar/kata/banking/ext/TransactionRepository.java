package fr.zbar.kata.banking.ext;

import fr.zbar.kata.banking.transaction.Transaction;
import fr.zbar.kata.banking.transaction.Transactions;

public interface TransactionRepository {

    Transactions all();

    void save(Transaction transaction);
}
