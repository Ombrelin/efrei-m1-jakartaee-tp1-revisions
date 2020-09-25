package fr.arsenelapostolet.bank.domain;

import java.math.BigDecimal;

public class Deposit extends Operation {
    public Deposit(BigDecimal montant) {
        super(montant);
    }
}
