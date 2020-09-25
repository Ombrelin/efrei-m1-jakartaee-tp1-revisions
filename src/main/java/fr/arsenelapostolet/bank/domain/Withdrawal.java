package fr.arsenelapostolet.bank.domain;

import java.math.BigDecimal;

public class Withdrawal extends Operation {
    public Withdrawal(BigDecimal montant) {
        super(montant);
    }
}
