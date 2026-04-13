package Task3;

public class LibraryService {
    private final BookManager bookManager;
    private final UserManager userManager;

    public LibraryService(BookManager bookManager, UserManager userManager) {
        this.bookManager = bookManager;
        this.userManager = userManager;
    }

    public String issueBook(int bookId, int userId) {
        Book book = bookManager.getBookById(bookId);
        if (book == null) {
            return "Book not found.";
        }

        User user = userManager.getUserById(userId);
        if (user == null) {
            return "User not found.";
        }

        if (book.isIssued()) {
            return "Book is already issued.";
        }

        book.issueToUser(userId);
        return "Book issued successfully.";
    }

    public String returnBook(int bookId) {
        Book book = bookManager.getBookById(bookId);
        if (book == null) {
            return "Book not found.";
        }

        if (!book.isIssued()) {
            return "Book is not currently issued.";
        }

        book.returnBook();
        return "Book returned successfully.";
    }

    public String removeBook(int bookId) {
        Book book = bookManager.getBookById(bookId);
        if (book == null) {
            return "Book not found.";
        }

        if (book.isIssued()) {
            return "Cannot remove an issued book.";
        }

        bookManager.removeBook(bookId);
        return "Book removed successfully.";
    }

    public String removeUser(int userId) {
        User user = userManager.getUserById(userId);
        if (user == null) {
            return "User not found.";
        }

        for (Book book : bookManager.getAllBooks()) {
            if (book.isIssued() && book.getIssuedToUserId() != null && book.getIssuedToUserId() == userId) {
                return "Cannot remove user with issued books.";
            }
        }

        userManager.removeUser(userId);
        return "User removed successfully.";
    }
}

