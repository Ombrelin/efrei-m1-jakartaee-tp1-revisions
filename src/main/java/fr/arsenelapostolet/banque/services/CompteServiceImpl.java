package fr.arsenelapostolet.banque.services;

import fr.arsenelapostolet.banque.domain.CompteCourant;
import fr.arsenelapostolet.banque.domain.CompteEpargne;
import fr.arsenelapostolet.banque.repostories.CompteRepository;

public class CompteServiceImpl implements CompteService {

    private CompteRepository accountRepository;

    public CompteServiceImpl(CompteRepository compteRepository) {
        this.accountRepository = compteRepository;
    }

    @Override
    public int createCurrentAccount(double overdraft) {
        var account = new CompteCourant(overdraft);
        this.accountRepository.insert(account);
        return account.getCode();
    }

    @Override
    public int createSavingsAccount(double interestRate) {
        var account = new CompteEpargne(interestRate);
        this.accountRepository.insert(account);
        return account.getCode();
    }

    @Override
    public void pay(int accountNumber, double amount) {
        var account = this.accountRepository.get(accountNumber);
        account.retirer(amount);
    }

    @Override
    public void withdraw(int accountNumber, double amount) {
        var account = this.accountRepository.get(accountNumber);
        account.retirer(amount);
    }

    @Override
    public void transfer(int sourceAccountNumber, int beneficiaryAccountNumber) {
        var sourceAccount = this.accountRepository.get(sourceAccountNumber);
        var beneficiaryAccount = this.accountRepository.get(beneficiaryAccountNumber);

        // TODO
    }

    @Override
    public void getBalance(int accountNumber) {

    }

    @Override
    public void getOperations(int accountNumber) {

    }

    @Override
    public void getPayments(int accountNumber) {

    }

    @Override
    public void getWithdrawals(int accountNumber) {

    }
}
