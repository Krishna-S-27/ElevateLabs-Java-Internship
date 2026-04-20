package Task6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public boolean addTask(String taskText) {
        String normalized = taskText == null ? "" : taskText.trim();
        if (normalized.isEmpty()) {
            return false;
        }

        tasks.add(new Task(normalized));
        return true;
    }

    public Task deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            return null;
        }
        return tasks.remove(index);
    }

    public boolean toggleTaskCompletion(int index) {
        if (index < 0 || index >= tasks.size()) {
            return false;
        }
        tasks.get(index).toggleCompleted();
        return true;
    }

    public int clearCompleted() {
        int removedCount = 0;
        for (int i = tasks.size() - 1; i >= 0; i--) {
            if (tasks.get(i).isCompleted()) {
                tasks.remove(i);
                removedCount++;
            }
        }
        return removedCount;
    }

    public int getCompletedCount() {
        int completedCount = 0;
        for (Task task : tasks) {
            if (task.isCompleted()) {
                completedCount++;
            }
        }
        return completedCount;
    }

    public int getPendingCount() {
        return tasks.size() - getCompletedCount();
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }
}

