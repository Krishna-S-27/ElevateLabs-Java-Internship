package Task2;

import java.util.Scanner;

public class StudentRecordManagement {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final StudentManager STUDENT_MANAGER = new StudentManager();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting Student Record Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select from 1 to 5.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== Student Record Management System =====");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Exit");
    }

    private static void addStudent() {
        int id = readInt("Enter student ID: ");

        if (STUDENT_MANAGER.getStudentById(id) != null) {
            System.out.println("A student with this ID already exists.");
            return;
        }

        String name = readNonEmptyString("Enter student name: ");
        int age = readPositiveInt("Enter student age: ");
        String grade = readNonEmptyString("Enter student grade: ");

        Student student = new Student(id, name, age, grade);
        STUDENT_MANAGER.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void viewAllStudents() {
        if (STUDENT_MANAGER.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\nStudent Records:");
        for (Student student : STUDENT_MANAGER.getAllStudents()) {
            System.out.println(student);
        }
    }

    private static void updateStudent() {
        int id = readInt("Enter student ID to update: ");
        Student existing = STUDENT_MANAGER.getStudentById(id);

        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }

        String name = readNonEmptyString("Enter new name: ");
        int age = readPositiveInt("Enter new age: ");
        String grade = readNonEmptyString("Enter new grade: ");

        STUDENT_MANAGER.updateStudent(id, name, age, grade);
        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() {
        int id = readInt("Enter student ID to delete: ");

        if (STUDENT_MANAGER.deleteStudent(id)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = SCANNER.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number. Please enter a valid integer.");
            }
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Value must be greater than 0.");
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = SCANNER.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Input cannot be empty.");
        }
    }
}
