package Task5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private static final DateTimeFormatter TS_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String accountNumber;
    private final String holderName;
    private double balance;
    private final List<String> transactionHistory;

    public Account(String accountNumber, String holderName, double initialBalance) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty.");
        }
        if (holderName == null || holderName.trim().isEmpty()) {
            throw new IllegalArgumentException("Holder name cannot be empty.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }

        this.accountNumber = accountNumber.trim();
        this.holderName = holderName.trim();
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();

        addTransaction(String.format("ACCOUNT_OPENED | Initial balance: %.2f", initialBalance));
    }

    public void deposit(double amount) {
        validatePositiveAmount(amount);
        balance += amount;
        addTransaction(String.format("DEPOSIT        | +%.2f | Balance: %.2f", amount, balance));
    }

    public void withdraw(double amount) {
        validatePositiveAmount(amount);
        if (amount > balance) {
            throw new IllegalStateException("Insufficient balance.");
        }

        balance -= amount;
        addTransaction(String.format("WITHDRAW       | -%.2f | Balance: %.2f", amount, balance));
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    @Override
    public String toString() {
        return String.format("Account{accountNumber='%s', holderName='%s', balance=%.2f}",
                accountNumber, holderName, balance);
    }

    private void addTransaction(String details) {
        transactionHistory.add("[" + LocalDateTime.now().format(TS_FORMAT) + "] " + details);
    }

    private void validatePositiveAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
    }
}

