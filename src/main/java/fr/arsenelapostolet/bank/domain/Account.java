package fr.arsenelapostolet.bank.domain;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public abstract class Account {
    private static int currentAccountNumber = 0;

    private final int accountNumber;
    private BigDecimal balance;
    private final List<Operation> operations = new LinkedList<>();

    public Account() {
        this.accountNumber = ++currentAccountNumber;
        this.balance = BigDecimal.ZERO;
    }

    public void deposit(BigDecimal amount) {
        this.operations.add(new Deposit(amount));
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (this.canWithdraw(amount)) {
            this.getOperations().add(new Withdrawal(amount));
            this.balance = this.balance.subtract(amount);
        } else {
            throw new IllegalStateException("Solde insuffisant");
        }
    }

    public abstract boolean canWithdraw(BigDecimal montant);

    public void transfer(BigDecimal amount, Account beneficiary) {
        if (this.canWithdraw(amount)) {
            this.withdraw(amount);
            beneficiary.deposit(amount);
        } else {
            throw new IllegalStateException("Solde insuffisant");
        }
    }

    public abstract void updateBalance();


    public List<Operation> getOperations() {
        return this.operations;
    }

    public BigDecimal getDepositsTotal() {
        return this.operations
                .stream()
                .filter(operation -> operation instanceof Deposit)
                .map(Operation::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public BigDecimal getWithdrawalsTotal() {
        return this.operations
                .stream()
                .filter(operation -> operation instanceof Withdrawal)
                .map(Operation::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
