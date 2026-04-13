package Task3;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class BookManager {
    private final Map<Integer, Book> books = new LinkedHashMap<>();

    public boolean addBook(Book book) {
        if (books.containsKey(book.getId())) {
            return false;
        }
        books.put(book.getId(), book);
        return true;
    }

    public Book getBookById(int id) {
        return books.get(id);
    }

    public boolean removeBook(int id) {
        return books.remove(id) != null;
    }

    public Collection<Book> getAllBooks() {
        return books.values();
    }
}

