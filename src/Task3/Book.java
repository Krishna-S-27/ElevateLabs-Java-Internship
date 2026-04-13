package Task3;

public class Book {
    private final int id;
    private final String title;
    private final String author;
    private boolean issued;
    private Integer issuedToUserId;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
        this.issuedToUserId = null;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return issued;
    }

    public Integer getIssuedToUserId() {
        return issuedToUserId;
    }

    public void issueToUser(int userId) {
        this.issued = true;
        this.issuedToUserId = userId;
    }

    public void returnBook() {
        this.issued = false;
        this.issuedToUserId = null;
    }

    @Override
    public String toString() {
        String status = issued ? "Issued to user ID " + issuedToUserId : "Available";
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

