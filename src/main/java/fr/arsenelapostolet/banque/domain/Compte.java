package fr.arsenelapostolet.banque.domain;

import java.util.LinkedList;
import java.util.List;

public abstract class Compte {
    private static int currentAccountNumber = 0;

    private int code;
    private double solde;
    private List<Operation> operations = new LinkedList<>();

    public Compte() {
        this.code = ++currentAccountNumber;
        this.solde = 0;
    }

    public void verser(double montant) {
        this.operations.add(new Versement(montant));
        this.solde += montant;
    }

    public void retirer(double montant) {
        if (this.peutRetirer(montant)) {
            this.listeOperation().add(new Retrait(montant));
            this.setSolde(this.getSolde() - montant);
        } else {
            throw new IllegalStateException("Solde insuffisant");
        }
    }

    public abstract boolean peutRetirer(double montant);

    public void virement(double montant, Compte beneficiaire) {
        if (this.peutRetirer(montant)) {
            this.retirer(montant);
            beneficiaire.verser(montant);
        } else {
            throw new IllegalStateException("Solde insuffisant");
        }
    }

    public double consulterSolde() {
        return this.solde;
    }

    public abstract void updateSolde();


    public List<Operation> listeOperation() {
        return this.operations;
    }

    public double totalVersements() {
        return this.operations
                .stream()
                .filter(operation -> operation instanceof Versement)
                .mapToDouble(Operation::getMontant)
                .sum();
    }

    public double totalRetraits() {
        return this.operations
                .stream()
                .filter(operation -> operation instanceof Retrait)
                .mapToDouble(Operation::getMontant)
                .sum();
    }

    public int getCode() {
        return code;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
}
