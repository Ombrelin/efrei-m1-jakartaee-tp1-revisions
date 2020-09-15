package fr.arsenelapostolet.banque.repostories;

import fr.arsenelapostolet.banque.domain.Compte;

import java.util.LinkedList;
import java.util.List;

public class InMemoryCompteRepository implements CompteRepository {

    private List<Compte> comptes = new LinkedList<>();

    @Override
    public void insert(Compte c) {
        comptes.add(c);
    }

    @Override
    public Compte get(int numero) {
        return comptes
                .stream()
                .filter(compte -> compte.getCode() == numero)
                .findAny()
                .orElseThrow(IllegalAccessError::new);
    }

    @Override
    public List<Compte> getAll() {
        return comptes;
    }
}
