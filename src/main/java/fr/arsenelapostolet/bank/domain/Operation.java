package fr.arsenelapostolet.bank.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Operation {
    private static int currentOperationNumber = 0;

    private final int numero;
    private final BigDecimal amount;
    private final LocalDate date;

    public Operation(BigDecimal montant) {
        this.numero = ++currentOperationNumber;
        this.amount = montant;
        this.date = LocalDate.now();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getNumero() {
        return numero;
    }

    public LocalDate getDate() {
        return date;
    }
}
