# Task 1 - CLI Calculator

A simple, professional command-line calculator built in Java.

It supports basic arithmetic operations and keeps running in a loop until the user chooses to exit.

## Features

- Interactive CLI prompts
- Supported operators: `+`, `-`, `*`, `/`
- Decimal number input support (`double`)
- Input validation for non-numeric values
- Division-by-zero protection
- Continuous loop mode until user enters `x` or `exit`

## Project File

- Source: `src/Task1/consoleCalculator.java`
- Package: `Task1`
- Main class: `consoleCalculator`

## Prerequisites

- Java Development Kit (JDK) installed
- `javac` and `java` available in your PATH

## Compile and Run (Windows PowerShell)

Run these commands from the project root:

```powershell
Set-Location "D:\ElevateLabs-Java-Internship"
javac src\Task1\consoleCalculator.java
java -cp src Task1.consoleCalculator
```

## How to Use

1. Start the program.
2. Enter an operator (`+`, `-`, `*`, `/`) or `x` to exit.
3. Enter the first and second numbers.
4. View the result.
5. Repeat as needed.

## Sample Session

```text
=== CLI Calculator ===
Type x anytime in the operator prompt to exit.

Choose operation: +  -  *  /   (or x to exit)
Operator: *
Enter the first number: 12.5
Enter the second number: 4
Result: 50.0000

Choose operation: +  -  *  /   (or x to exit)
Operator: /
Enter the first number: 10
Enter the second number: 0
Error: Division by zero is not allowed. Please try again.

Choose operation: +  -  *  /   (or x to exit)
Operator: x
Thank you for using the calculator. Goodbye!
```

## Notes

- If you enter invalid numeric input (for example, `abc`), the calculator asks again.
- Results are printed with 4 decimal places.

