package fr.arsenelapostolet.bank.domain;

import java.math.BigDecimal;

public class CurrentAccount extends Account {
    private final BigDecimal overdraft;

    public CurrentAccount(BigDecimal overdraft) {
        this.overdraft = overdraft;
    }

    @Override
    public boolean canWithdraw(BigDecimal amount) {
        return this.getBalance().subtract(amount).compareTo(this.overdraft.negate()) >= 0;
    }

    @Override
    public void updateBalance() {
        throw new UnsupportedOperationException("Les comptes courants n'ont pas d'intérêts");
    }

    public BigDecimal getOverdraft() {
        return overdraft;
    }
}
