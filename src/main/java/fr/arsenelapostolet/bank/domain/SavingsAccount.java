package fr.arsenelapostolet.bank.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SavingsAccount extends Account {

    private final BigDecimal interestRate;

    public SavingsAccount(BigDecimal interestRate) {
        this.interestRate = interestRate;

        if (this.interestRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le taux d'intéret doit être positif");
        }
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    @Override
    public boolean canWithdraw(BigDecimal montant) {
        return this.getBalance().subtract(montant).compareTo(BigDecimal.ZERO) >= 0;
    }

    @Override
    public void updateBalance() {
        this.setBalance(this.getBalance().add(this.getBalance().multiply(this.interestRate.divide(new BigDecimal(100)))));
    }
}
