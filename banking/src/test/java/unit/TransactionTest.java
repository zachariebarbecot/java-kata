package unit;

import fr.zbar.kata.banking.Account;
import fr.zbar.kata.banking.transaction.Amount;
import fr.zbar.kata.banking.ext.TransactionRepository;
import fr.zbar.kata.banking.transaction.DepositTransaction;
import fr.zbar.kata.banking.transaction.Transaction;
import fr.zbar.kata.banking.transaction.WithdrawTransaction;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.mockito.Mockito.*;

public class TransactionTest {

    private final Clock clock = Clock.fixed(LocalDate.of(2015, Month.DECEMBER, 24).atStartOfDay().toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));
    private final TransactionRepository transactionRepository = mock(TransactionRepository.class);
    private final Account account = new Account(clock, transactionRepository);

    @Test
    void should_deposit() {
        Amount depositAmount = new Amount(500);
        Transaction depositTransaction = new DepositTransaction(LocalDate.now(clock), depositAmount);

        account.deposit(depositAmount.value());

        verify(transactionRepository, times(1))
                .save(depositTransaction);
    }

    @Test
    void should_withdraw() {
        Amount withdrawAmount = new Amount(500);
        Transaction withdrawTransaction = new WithdrawTransaction(LocalDate.now(clock), withdrawAmount);

        account.withdraw(withdrawAmount.value());

        verify(transactionRepository, times(1))
                .save(withdrawTransaction);
    }
}
