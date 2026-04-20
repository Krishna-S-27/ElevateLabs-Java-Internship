package Task6;

public class Task {
    private final String text;
    private boolean completed;

    public Task(String text) {
        this.text = text;
        this.completed = false;
    }

    public String getText() {
        return text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void toggleCompleted() {
        completed = !completed;
    }

    @Override
    public String toString() {
        return (completed ? "[x] " : "[ ] ") + text;
    }
}

