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
    public void withdraw(BigDecimal amount) {
        if(this.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0){
            super.withdraw(amount);
        }
        else {
            throw new IllegalStateException("Solde insuffisant");
        }
    }

    @Override
    public void updateBalance() {
        this.setBalance(this.getBalance().add(this.getBalance().multiply(this.interestRate.divide(new BigDecimal(100)))));
    }
}
