package fr.arsenelapostolet.bank.repositories;

import fr.arsenelapostolet.bank.domain.Account;

import java.util.List;

public interface AccountRepository {
    void insert(Account c);
    Account get(int numero);
    List<Account> getAll();
}
