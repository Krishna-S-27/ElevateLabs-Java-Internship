package Task6;

public class TaskManagerSmokeTest {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        assertCondition(!manager.addTask("   "), "Blank task should not be added");
        assertCondition(manager.addTask("Complete Java Swing task"), "Valid task should be added");
        assertCondition(manager.addTask("Review README"), "Second task should be added");

        assertCondition(manager.getTasks().size() == 2, "Task count should be 2");
        assertCondition("Complete Java Swing task".equals(manager.getTasks().get(0).getText()), "First task text mismatch");

        assertCondition(manager.toggleTaskCompletion(0), "Toggle should succeed for valid index");
        assertCondition(manager.getTasks().get(0).isCompleted(), "First task should be marked completed");
        assertCondition(manager.getCompletedCount() == 1, "Completed count should be 1");
        assertCondition(manager.getPendingCount() == 1, "Pending count should be 1");

        Task removed = manager.deleteTask(0);
        assertCondition(removed != null, "Deleted task should not be null");
        assertCondition(manager.getTasks().size() == 1, "Task count should be 1 after delete");

        manager.toggleTaskCompletion(0);
        int cleared = manager.clearCompleted();
        assertCondition(cleared == 1, "One completed task should be cleared");
        assertCondition(manager.getTasks().isEmpty(), "All tasks should be removed after clear completed");

        System.out.println("TaskManager smoke test passed.");
    }

    private static void assertCondition(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException("Smoke test failed: " + message);
        }
    }
}

