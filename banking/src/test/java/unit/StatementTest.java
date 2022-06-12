package unit;

import fr.zbar.kata.banking.Account;
import fr.zbar.kata.banking.ext.TransactionRepository;
import fr.zbar.kata.banking.transaction.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StatementTest {

    private final Clock clock = Clock.fixed(LocalDate.of(2015, Month.DECEMBER, 24).atStartOfDay().toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));
    private final TransactionRepository transactionRepository = mock(TransactionRepository.class);
    private final Account account = new Account(clock, transactionRepository);

    @Test
    void should_print_empty_statement_when_no_transaction() {
        when(transactionRepository.all()).thenReturn(Transactions.EMPTY);

        Assertions.assertThat(account.printStatement())
                .isEqualTo("""
                        Date;Amount;Balance;
                        """);
    }

    @Test
    void should_print_one_line_statement_when_one_deposit_is_done() {
        Transaction depositTransaction = new DepositTransaction(LocalDate.now(clock), new Amount(500));
        when(transactionRepository.all())
                .thenReturn(new Transactions(List.of(depositTransaction)));

        Assertions.assertThat(account.printStatement())
                .isEqualTo("""
                        Date;Amount;Balance;
                        24.12.2015;+500;500;
                        """);

    }

    @Test
    void should_print_one_line_statement_when_one_withdraw_is_done() {
        Transaction withdrawTransaction = new WithdrawTransaction(LocalDate.now(clock), new Amount(500));
        when(transactionRepository.all())
                .thenReturn(new Transactions(List.of(withdrawTransaction)));

        Assertions.assertThat(account.printStatement())
                .isEqualTo("""
                        Date;Amount;Balance;
                        24.12.2015;-500;-500;
                        """);

    }

    @Test
    void should_print_multiple_lines_statement_when_multiple_deposits_are_done() {
        Amount depositAmount = new Amount(500);
        Transaction depositTransaction1 = new DepositTransaction(LocalDate.now(clock), depositAmount);
        Transaction depositTransaction2 = new DepositTransaction(LocalDate.now(clock), depositAmount);
        when(transactionRepository.all())
                .thenReturn(new Transactions(List.of(depositTransaction1, depositTransaction2)));

        Assertions.assertThat(account.printStatement())
                .isEqualTo("""
                        Date;Amount;Balance;
                        24.12.2015;+500;500;
                        24.12.2015;+500;1000;
                        """);

    }

    @Test
    void should_print_multiple_lines_statement_when_multiple_withdraw_are_done() {
        Amount withdrawAmount = new Amount(500);
        Transaction withdrawTransaction1 = new WithdrawTransaction(LocalDate.now(clock), withdrawAmount);
        Transaction withdrawTransaction2 = new WithdrawTransaction(LocalDate.now(clock), withdrawAmount);
        when(transactionRepository.all())
                .thenReturn(new Transactions(List.of(withdrawTransaction1, withdrawTransaction2)));

        Assertions.assertThat(account.printStatement())
                .isEqualTo("""
                        Date;Amount;Balance;
                        24.12.2015;-500;-500;
                        24.12.2015;-500;-1000;
                        """);

    }

    @Test
    void should_print_multiple_lines_statement_when_multiple_deposit_and_withdraw_are_done() {
        Amount depositAmount = new Amount(500);
        Amount withdrawAmount = new Amount(250);
        Transaction depositTransaction1 = new DepositTransaction(LocalDate.now(clock), depositAmount);
        Transaction withdrawTransaction1 = new WithdrawTransaction(LocalDate.now(clock), withdrawAmount);
        Transaction depositTransaction2 = new DepositTransaction(LocalDate.now(clock), depositAmount);
        Transaction withdrawTransaction2 = new WithdrawTransaction(LocalDate.now(clock), withdrawAmount);
        when(transactionRepository.all())
                .thenReturn(new Transactions(List.of(depositTransaction1, withdrawTransaction1, depositTransaction2, withdrawTransaction2)));

        Assertions.assertThat(account.printStatement())
                .isEqualTo("""
                        Date;Amount;Balance;
                        24.12.2015;+500;500;
                        24.12.2015;-250;250;
                        24.12.2015;+500;750;
                        24.12.2015;-250;500;
                        """);
    }
}
