package fr.arsenelapostolet.banque.domain;

import java.time.LocalDate;

public abstract class Operation {
    private static int currentOperationNumber = 0;

    private int numero;
    private double montant;
    private LocalDate date;

    public Operation(double montant) {
        this.numero = ++currentOperationNumber;
        this.montant = montant;
        this.date = LocalDate.now();
    }

    public double getMontant() {
        return montant;
    }

    public int getNumero() {
        return numero;
    }

    public LocalDate getDate() {
        return date;
    }
}
