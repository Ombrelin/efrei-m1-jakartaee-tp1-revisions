package fr.arsenelapostolet.bank.services;

import fr.arsenelapostolet.bank.domain.CurrentAccount;
import fr.arsenelapostolet.bank.domain.Operation;
import fr.arsenelapostolet.bank.domain.SavingsAccount;
import fr.arsenelapostolet.bank.repositories.AccountRepository;

import java.math.BigDecimal;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public int createCurrentAccount(BigDecimal overdraft) {
        var account = new CurrentAccount(overdraft);
        this.accountRepository.insert(account);
        return account.getAccountNumber();
    }

    @Override
    public int createSavingsAccount(BigDecimal interestRate) {
        var account = new SavingsAccount(interestRate);
        this.accountRepository.insert(account);
        return account.getAccountNumber();
    }

    @Override
    public void pay(int accountNumber, BigDecimal amount) {
        var account = this.accountRepository.get(accountNumber);
        account.withdraw(amount);
    }

    @Override
    public void withdraw(int accountNumber, BigDecimal amount) {
        var account = this.accountRepository.get(accountNumber);
        account.withdraw(amount);
    }

    @Override
    public void transfer(int sourceAccountNumber, int beneficiaryAccountNumber, BigDecimal amount) {
        var sourceAccount = this.accountRepository.get(sourceAccountNumber);
        var beneficiaryAccount = this.accountRepository.get(beneficiaryAccountNumber);
        sourceAccount.transfer(amount, beneficiaryAccount);
    }

    @Override
    public BigDecimal getBalance(int accountNumber) {
        var account = this.accountRepository.get(accountNumber);
        return account.getBalance();
    }

    @Override
    public List<Operation> getOperations(int accountNumber) {
        var account = this.accountRepository.get(accountNumber);
        return account.getOperations();
    }

    @Override
    public BigDecimal getDepositsTotal(int accountNumber) {
        var account = this.accountRepository.get(accountNumber);
        return account.getDepositsTotal();
    }

    @Override
    public BigDecimal getWithdrawalsTotal(int accountNumber) {
        var account = this.accountRepository.get(accountNumber);
        return account.getWithdrawalsTotal();
    }


}
