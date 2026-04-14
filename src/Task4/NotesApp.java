package Task4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class NotesApp {
    private static final String NOTES_FILE = "src/Task4/notes.txt";

    private final Scanner scanner = new Scanner(System.in);
    private final List<Note> notes = new ArrayList<>();

    public static void main(String[] args) {
        new NotesApp().run();
    }

    private void run() {
        loadNotes();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    viewAllNotes();
                    break;
                case 3:
                    searchNotes();
                    break;
                case 4:
                    deleteNote();
                    break;
                case 5:
                    saveNotes();
                    System.out.println("Notes saved. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Notes App ===");
        System.out.println("1. Add Note");
        System.out.println("2. View All Notes");
        System.out.println("3. Search Notes");
        System.out.println("4. Delete Note");
        System.out.println("5. Save and Exit");
    }

    private void addNote() {
        String title = readNonEmpty("Enter note title: ");
        String content = readNonEmpty("Enter note content: ");
        int nextId = getNextId();

        notes.add(new Note(nextId, title, content));
        saveNotes();
        System.out.println("Note added with ID " + nextId + ".");
    }

    private void viewAllNotes() {
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
            return;
        }

        System.out.println("\nAll Notes:");
        for (Note note : notes) {
            System.out.println(note);
        }
    }

    private void searchNotes() {
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
            return;
        }

        String keyword = readNonEmpty("Enter keyword to search: ").toLowerCase(Locale.ROOT);
        boolean found = false;

        for (Note note : notes) {
            String title = note.title.toLowerCase(Locale.ROOT);
            String content = note.content.toLowerCase(Locale.ROOT);
            if (title.contains(keyword) || content.contains(keyword)) {
                if (!found) {
                    System.out.println("\nSearch Results:");
                }
                System.out.println(note);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No notes matched your keyword.");
        }
    }

    private void deleteNote() {
        if (notes.isEmpty()) {
            System.out.println("No notes to delete.");
            return;
        }

        int id = readInt("Enter note ID to delete: ");
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).id == id) {
                notes.remove(i);
                saveNotes();
                System.out.println("Note deleted successfully.");
                return;
            }
        }

        System.out.println("Note ID not found.");
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

    private int getNextId() {
        int maxId = 0;
        for (Note note : notes) {
            if (note.id > maxId) {
                maxId = note.id;
            }
        }
        return maxId + 1;
    }

    private void loadNotes() {
        File file = new File(NOTES_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|", 3);
                if (parts.length < 3) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(parts[0]);
                    String title = unescape(parts[1]);
                    String content = unescape(parts[2]);
                    notes.add(new Note(id, title, content));
                } catch (NumberFormatException ignored) {
                    // Skip malformed lines and continue loading valid entries.
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load notes: " + e.getMessage());
        }
    }

    private void saveNotes() {
        File file = new File(NOTES_FILE);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Note note : notes) {
                String line = note.id + "|" + escape(note.title) + "|" + escape(note.content);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not save notes: " + e.getMessage());
        }
    }

    private String escape(String text) {
        return text.replace("\\", "\\\\").replace("|", "\\|").replace("\n", "\\n");
    }

    private String unescape(String text) {
        StringBuilder result = new StringBuilder();
        boolean escaping = false;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (escaping) {
                if (ch == 'n') {
                    result.append('\n');
                } else {
                    result.append(ch);
                }
                escaping = false;
            } else if (ch == '\\') {
                escaping = true;
            } else {
                result.append(ch);
            }
        }

        if (escaping) {
            result.append('\\');
        }

        return result.toString();
    }

    private static class Note {
        private final int id;
        private final String title;
        private final String content;

        private Note(int id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | Title: " + title + " | Content: " + content;
        }
    }
}

