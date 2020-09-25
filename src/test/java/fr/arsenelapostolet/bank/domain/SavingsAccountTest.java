package fr.arsenelapostolet.bank.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class SavingsAccountTest {

    @Test
    public void initNominalTest(){
        // When
        var account = new SavingsAccount(new BigDecimal("1.5"));

        // Then
        assertEquals(new BigDecimal("1.5"), account.getInterestRate());
    }


    @Test
    public void initNegativeRateTest(){
        // When / Then
        assertThrows(IllegalArgumentException.class, () -> {
                var account = new SavingsAccount(new BigDecimal("-1.5"));
        });

    }

    @Test
    public void withdrawTestNotEnoughTest(){
        // Given
        var account = new SavingsAccount(new BigDecimal("1.5"));
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
    public void updateBalanceTest(){
        // Given
        var account = new SavingsAccount(new BigDecimal("1.5"));
        account.deposit(new BigDecimal(1550));

        // When
        account.updateBalance();

        // Then
        assertEquals(new BigDecimal("1573.250"), account.getBalance());
    }


}
