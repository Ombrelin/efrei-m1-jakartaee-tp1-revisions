package fr.arsenelapostolet.banque.services;

public interface CompteService {
    int createCurrentAccount(double overdraft);
    int createSavingsAccount(double interestRate);
    void pay(int accountNumber, double amount);
    void withdraw(int accountNumber, double amount);
    void transfer(int sourceAccountNumber, int beneficiaryAccountNumber);
    void getBalance(int accountNumber);
    void getOperations(int accountNumber);
    void getPayments(int accountNumber);
    void getWithdrawals(int accountNumber);
}
