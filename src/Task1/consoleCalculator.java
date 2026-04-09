package Task1;

import java.util.Locale;
import java.util.Scanner;

public class consoleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("=== CLI Calculator ===");
        System.out.println("Type x anytime in the operator prompt to exit.");

        while (true) {
            System.out.println();
            System.out.println("Choose operation: +  -  *  /   (or x to exit)");
            System.out.print("Operator: ");
            String operator = scanner.next().trim();

            if (operator.equalsIgnoreCase("x") || operator.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using the calculator. Goodbye!");
                break;
            }

            if (!isValidOperator(operator)) {
                System.out.println("Invalid operator. Please choose one of +, -, *, /, or x to exit.");
                continue;
            }

            double num1 = readNumber(scanner, "Enter the first number: ");
            double num2 = readNumber(scanner, "Enter the second number: ");

            if (operator.equals("/") && num2 == 0) {
                System.out.println("Error: Division by zero is not allowed. Please try again.");
                continue;
            }

            double result = calculate(operator, num1, num2);
            System.out.printf("Result: %.4f%n", result);
        }

        scanner.close();
    }

    private static boolean isValidOperator(String operator) {
        return operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/");
    }

    private static double readNumber(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }
            String invalidInput = scanner.next();
            System.out.println("Invalid number '" + invalidInput + "'. Please enter a valid numeric value.");
        }
    }

    private static double calculate(String operator, double num1, double num2) {
        switch (operator) {
            case "+":
                return addition(num1, num2);
            case "-":
                return subtraction(num1, num2);
            case "*":
                return multiplication(num1, num2);
            case "/":
                return division(num1, num2);
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }

    private static double division(double num1, double num2) {
        return num1 / num2;
    }

    private static double multiplication(double num1, double num2) {
        return num1 * num2;
    }

    private static double subtraction(double num1, double num2) {
        return num1 - num2;
    }

    private static double addition(double num1, double num2) {
        return num1 + num2;
    }
}
