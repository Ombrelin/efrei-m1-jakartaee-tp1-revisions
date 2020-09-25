package fr.arsenelapostolet.bank.services;

import fr.arsenelapostolet.bank.domain.Operation;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    int createCurrentAccount(BigDecimal overdraft);

    int createSavingsAccount(BigDecimal interestRate);

    void pay(int accountNumber, BigDecimal amount);

    void withdraw(int accountNumber, BigDecimal amount);

    void transfer(int sourceAccountNumber, int beneficiaryAccountNumber, BigDecimal amount);

    BigDecimal getBalance(int accountNumber);

    List<Operation> getOperations(int accountNumber);

    BigDecimal getDepositsTotal(int accountNumber);

    BigDecimal getWithdrawalsTotal(int accountNumber);
}
