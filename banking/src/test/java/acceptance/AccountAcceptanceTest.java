package acceptance;

import fr.zbar.kata.banking.Account;
import fr.zbar.kata.banking.ext.TransactionRepository;
import fr.zbar.kata.banking.transaction.Transaction;
import fr.zbar.kata.banking.transaction.Transactions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Collections;
import java.util.stream.Stream;

class AccountAcceptanceTest {

    private final Clock clock = Clock.fixed(LocalDate.of(2015, Month.DECEMBER, 24).atStartOfDay().toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));
    private final TransactionRepository transactionRepository = new FakeTransactionRepository();
    private final Account account = new Account(clock, transactionRepository);

    @Test
    void should_print_statement_after_some_transactions() {
        account.deposit(500);
        account.withdraw(250);

        String result = account.printStatement();

        Assertions.assertThat(result)
                .isEqualTo("""
                        Date;Amount;Balance;
                        24.12.2015;+500;500;
                        24.12.2015;-250;250;
                        """);
    }

    private static final class FakeTransactionRepository implements TransactionRepository {

        private Transactions transactions = new Transactions(Collections.emptyList());

        @Override
        public Transactions all() {
            return transactions;
        }

        @Override
        public void save(Transaction transaction) {
            transactions = new Transactions(
                    Stream.concat(
                                    transactions.values().stream(),
                                    Stream.of(transaction)
                            )
                            .toList()
            );
        }
    }

}
