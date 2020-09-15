package fr.arsenelapostolet.banque.domain;

public class CompteCourant extends Compte {
    private double decouvert;

    public CompteCourant(double decouvert) {
        this.decouvert = decouvert;
    }

    @Override
    public boolean peutRetirer(double montant) {
        return (this.getSolde() - montant) >= -this.decouvert;
    }

    @Override
    public void updateSolde() {
        throw new UnsupportedOperationException("Les comptes courants n'ont pas d'intérêts");
    }
}
