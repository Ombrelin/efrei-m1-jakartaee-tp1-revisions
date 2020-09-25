package fr.arsenelapostolet.bank.domain;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

    private final BigDecimal tauxInteret;

    public SavingsAccount(BigDecimal tauxInteret) {
        this.tauxInteret = tauxInteret;

        if (this.tauxInteret.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le taux d'intéret doit être positif");
        }
    }



    @Override
    public boolean canWithdraw(BigDecimal montant) {
        return this.getBalance().subtract(montant).compareTo(BigDecimal.ZERO) >= 0;
    }

    @Override
    public void updateBalance() {
        this.setBalance(this.getBalance().multiply(this.tauxInteret));
    }
}
