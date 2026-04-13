package Task3;

import java.util.Scanner;

public class LibraryManagementSystem {
    private final Scanner scanner = new Scanner(System.in);
    private final BookManager bookManager = new BookManager();
    private final UserManager userManager = new UserManager();
    private final LibraryService libraryService = new LibraryService(bookManager, userManager);

    public static void main(String[] args) {
        new LibraryManagementSystem().run();
    }

    private void run() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    removeBook();
                    break;
                case 4:
                    addUser();
                    break;
                case 5:
                    viewUsers();
                    break;
                case 6:
                    removeUser();
                    break;
                case 7:
                    issueBook();
                    break;
                case 8:
                    returnBook();
                    break;
                case 9:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Library Management System ===");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Remove Book");
        System.out.println("4. Add User");
        System.out.println("5. View All Users");
        System.out.println("6. Remove User");
        System.out.println("7. Issue Book");
        System.out.println("8. Return Book");
        System.out.println("9. Exit");
    }

    private void addBook() {
        int id = readInt("Enter book ID: ");
        String title = readNonEmpty("Enter title: ");
        String author = readNonEmpty("Enter author: ");

        boolean added = bookManager.addBook(new Book(id, title, author));
        System.out.println(added ? "Book added successfully." : "Book ID already exists.");
    }

    private void viewBooks() {
        if (bookManager.getAllBooks().isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        System.out.println("\nBooks:");
        for (Book book : bookManager.getAllBooks()) {
            System.out.println(book);
        }
    }

    private void removeBook() {
        int id = readInt("Enter book ID to remove: ");
        System.out.println(libraryService.removeBook(id));
    }

    private void addUser() {
        int id = readInt("Enter user ID: ");
        String name = readNonEmpty("Enter name: ");
        String email = readNonEmpty("Enter email: ");

        boolean added = userManager.addUser(new User(id, name, email));
        System.out.println(added ? "User added successfully." : "User ID already exists.");
    }

    private void viewUsers() {
        if (userManager.getAllUsers().isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        System.out.println("\nUsers:");
        for (User user : userManager.getAllUsers()) {
            System.out.println(user);
        }
    }

    private void removeUser() {
        int id = readInt("Enter user ID to remove: ");
        System.out.println(libraryService.removeUser(id));
    }

    private void issueBook() {
        int bookId = readInt("Enter book ID: ");
        int userId = readInt("Enter user ID: ");
        System.out.println(libraryService.issueBook(bookId, userId));
    }

    private void returnBook() {
        int bookId = readInt("Enter book ID to return: ");
        System.out.println(libraryService.returnBook(bookId));
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Value cannot be empty.");
        }
    }
}

