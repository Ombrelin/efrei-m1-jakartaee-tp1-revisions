package fr.arsenelapostolet.banque.repostories;

import fr.arsenelapostolet.banque.domain.Compte;

import java.util.List;

public interface CompteRepository {
    void insert(Compte c);
    Compte get(int numero);
    List<Compte> getAll();
}
