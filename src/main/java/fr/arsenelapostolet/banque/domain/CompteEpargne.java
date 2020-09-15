package fr.arsenelapostolet.banque.domain;

public class CompteEpargne extends Compte {

    private double tauxInteret;

    public CompteEpargne(double tauxInteret) {
        if(this.tauxInteret < 0){
            throw new IllegalArgumentException("Le taux d'intéret doit être positif");
        }
        this.tauxInteret = tauxInteret;
    }

    @Override
    public boolean peutRetirer(double montant) {
        return (this.getSolde() - montant) >= 0;
    }

    @Override
    public void updateSolde() {
        this.setSolde(this.getSolde() * tauxInteret);
    }
}
