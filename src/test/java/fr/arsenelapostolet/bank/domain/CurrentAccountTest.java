package fr.arsenelapostolet.bank.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CurrentAccountTest {

    @Test
    public void withdrawOverdraftTest(){
        // Given
        var account = new CurrentAccount(new BigDecimal(150));
        account.deposit(new BigDecimal(1550));

        // When
        account.withdraw(new BigDecimal(1650));

        // Then
        assertEquals(new BigDecimal(-100), account.getBalance());
    }

    @Test
    public void updateBalanceTest(){
        // Given
        var account = new CurrentAccount(new BigDecimal(150));
        account.deposit(new BigDecimal(1550));

        // When / Then
        assertThrows(UnsupportedOperationException.class, account::updateBalance);
    }

}
