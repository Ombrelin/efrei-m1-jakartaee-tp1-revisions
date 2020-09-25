package fr.arsenelapostolet.bank.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {


    @Test
    public void accountInitTest(){
        // When
        var account = new CurrentAccount(new BigDecimal(150));

        // Then
        assertEquals(BigDecimal.ZERO, account.getBalance());
        assertEquals(new BigDecimal(150), account.getOverdraft());
    }

    @Test
    public void depositTestTest(){
        // Given
        var account = new CurrentAccount(new BigDecimal(150));

        // When
        account.deposit(new BigDecimal(1550));

        // Then
        assertEquals(1,account.getOperations().size());
        assertTrue(account.getOperations().get(0) instanceof Deposit);
        assertEquals(new BigDecimal(1550),account.getOperations().get(0).getAmount());
        assertEquals(LocalDate.now(),account.getOperations().get(0).getDate());
        assertEquals(new BigDecimal(1550),account.getBalance());
    }

    @Test
    public void withdrawTestNominalTest(){
        // Given
        var account = new CurrentAccount(new BigDecimal(150));
        account.deposit(new BigDecimal(1550));

        // When
        account.withdraw(new BigDecimal(1000));

        // Then
        assertEquals(2,account.getOperations().size());
        assertTrue(account.getOperations().get(1) instanceof Withdrawal);
        assertEquals(new BigDecimal(1000),account.getOperations().get(1).getAmount());
        assertEquals(new BigDecimal(1550 - 1000),account.getBalance());
    }

    @Test
    public void withdrawTestNotEnoughTest(){
        // Given
        var account = new CurrentAccount(new BigDecimal(150));
        account.deposit(new BigDecimal(1550));

        // When / Then
        assertThrows(IllegalStateException.class, () -> {
            account.withdraw(new BigDecimal(2000));
        });

        assertEquals(1,account.getOperations().size());
        assertTrue(account.getOperations().get(0) instanceof Deposit);
        assertEquals(new BigDecimal(1550),account.getOperations().get(0).getAmount());
        assertEquals(new BigDecimal(1550),account.getBalance());
    }

    @Test
    public void depositTotalTest(){
        // Given
        var account = new CurrentAccount(new BigDecimal(150));
        account.deposit(new BigDecimal(1550));
        account.deposit(new BigDecimal(300));
        account.deposit(new BigDecimal(650));
        account.withdraw(new BigDecimal(100));

        // When
        var result = account.getDepositsTotal();

        // Then
        assertEquals(new BigDecimal(2500), result);
    }

    @Test
    public void withdrawalTotalTest(){
        // Given
        var account = new CurrentAccount(new BigDecimal(150));
        account.deposit(new BigDecimal(3000));

        account.withdraw(new BigDecimal(1550));
        account.withdraw(new BigDecimal(300));
        account.withdraw(new BigDecimal(650));


        // When
        var result = account.getWithdrawalsTotal();

        // Then
        assertEquals(new BigDecimal(2500), result);
    }

    @Test
    public void transferNominalTest(){
        // Given
        var account = new CurrentAccount(new BigDecimal(150));
        account.deposit(new BigDecimal(1550));
        var beneficiaryAccount = new CurrentAccount(new BigDecimal(150));
        beneficiaryAccount.deposit(new BigDecimal(100));

        // When
        account.transfer(new BigDecimal(1000), beneficiaryAccount);

        // Then
        assertEquals(2,account.getOperations().size());
        assertTrue(account.getOperations().get(1) instanceof Withdrawal);
        assertEquals(new BigDecimal(1000),account.getOperations().get(1).getAmount());
        assertEquals(new BigDecimal(1550 - 1000),account.getBalance());

        assertEquals(2,beneficiaryAccount.getOperations().size());
        assertTrue(beneficiaryAccount.getOperations().get(1) instanceof Deposit);
        assertEquals(new BigDecimal(1000),beneficiaryAccount.getOperations().get(1).getAmount());
        assertEquals(new BigDecimal(100 + 1000),beneficiaryAccount.getBalance());
    }

    @Test
    public void transfeNotEnoughTest(){
        // Given
        var account = new CurrentAccount(new BigDecimal(150));
        account.deposit(new BigDecimal(1550));
        var beneficiaryAccount = new CurrentAccount(new BigDecimal(150));
        beneficiaryAccount.deposit(new BigDecimal(100));

        // When
        assertThrows(IllegalStateException.class, () -> {
            account.transfer(new BigDecimal(3000), beneficiaryAccount);
        });
    }

}
