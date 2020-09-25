package fr.arsenelapostolet.bank.repositories;

import fr.arsenelapostolet.bank.domain.Account;

import java.util.LinkedList;
import java.util.List;

public class InMemoryAccountRepository implements AccountRepository {

    private List<Account> comptes = new LinkedList<>();

    @Override
    public void insert(Account account) {
        comptes.add(account);
    }

    @Override
    public Account get(int accountNumber) {
        return comptes
                .stream()
                .filter(account -> account.getAccountNumber() == accountNumber)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Account> getAll() {
        return comptes;
    }
}
