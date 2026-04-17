package Task5;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class BankAccountSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("=== Bank Account Simulation ===");
        Account account = createAccount(scanner);

        while (true) {
            printMenu();
            int choice = readInt(scanner, "Choose an option: ");

            switch (choice) {
                case 1:
                    handleDeposit(scanner, account);
                    break;
                case 2:
                    handleWithdraw(scanner, account);
                    break;
                case 3:
                    System.out.printf("Current balance: %.2f%n", account.getBalance());
                    break;
                case 4:
                    printHistory(account.getTransactionHistory());
                    break;
                case 5:
                    System.out.println(account);
                    break;
                case 0:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose from the menu.");
            }
        }
    }

    private static Account createAccount(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine().trim();

        while (accountNumber.isEmpty()) {
            System.out.print("Account number cannot be empty. Enter account number: ");
            accountNumber = scanner.nextLine().trim();
        }

        System.out.print("Enter account holder name: ");
        String holderName = scanner.nextLine().trim();

        while (holderName.isEmpty()) {
            System.out.print("Holder name cannot be empty. Enter account holder name: ");
            holderName = scanner.nextLine().trim();
        }

        double initialBalance = readDouble(scanner, "Enter initial balance: ");
        while (initialBalance < 0) {
            System.out.println("Initial balance cannot be negative.");
            initialBalance = readDouble(scanner, "Enter initial balance: ");
        }

        Account account = new Account(accountNumber, holderName, initialBalance);
        System.out.println("Account created successfully!");
        System.out.println(account);
        return account;
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("--------- MENU ---------");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. View Balance");
        System.out.println("4. View Transaction History");
        System.out.println("5. View Account Details");
        System.out.println("0. Exit");
    }

    private static void handleDeposit(Scanner scanner, Account account) {
        double amount = readDouble(scanner, "Enter amount to deposit: ");
        try {
            account.deposit(amount);
            System.out.printf("Deposit successful. New balance: %.2f%n", account.getBalance());
        } catch (IllegalArgumentException ex) {
            System.out.println("Deposit failed: " + ex.getMessage());
        }
    }

    private static void handleWithdraw(Scanner scanner, Account account) {
        double amount = readDouble(scanner, "Enter amount to withdraw: ");
        try {
            account.withdraw(amount);
            System.out.printf("Withdrawal successful. New balance: %.2f%n", account.getBalance());
        } catch (IllegalArgumentException | IllegalStateException ex) {
            System.out.println("Withdrawal failed: " + ex.getMessage());
        }
    }

    private static void printHistory(List<String> history) {
        if (history.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("----- Transaction History -----");
        for (String entry : history) {
            System.out.println(entry);
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter an integer value.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}

