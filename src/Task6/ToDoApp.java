package Task6;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ToDoApp extends JFrame {
    private final TaskManager taskManager = new TaskManager();
    private final DefaultListModel<Task> listModel = new DefaultListModel<>();
    private final JList<Task> taskList = new JList<>(listModel);
    private final JTextField taskInput = new JTextField(24);
    private final JLabel statusLabel = new JLabel();

    public ToDoApp() {
        super("Task 6 - Java Swing To-Do App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        add(createTopPanel(), BorderLayout.NORTH);
        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        taskList.setVisibleRowCount(12);
        setPreferredSize(new Dimension(450, 360));
        pack();
        setLocationRelativeTo(null);
        updateStatus();
    }

    private JPanel createTopPanel() {
        JButton addButton = new JButton("Add Task");

        addButton.addActionListener(e -> addTask());
        taskInput.addActionListener(e -> addTask());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(taskInput);
        topPanel.add(addButton);
        return topPanel;
    }

    private JPanel createBottomPanel() {
        JButton toggleButton = new JButton("Complete / Undo");
        JButton deleteButton = new JButton("Delete Task");
        JButton clearCompletedButton = new JButton("Clear Completed");

        toggleButton.addActionListener(e -> toggleSelectedTask());
        deleteButton.addActionListener(e -> deleteTask());
        clearCompletedButton.addActionListener(e -> clearCompletedTasks());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(toggleButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(clearCompletedButton);
        bottomPanel.add(statusLabel);
        return bottomPanel;
    }

    private void addTask() {
        String text = taskInput.getText();
        if (!taskManager.addTask(text)) {
            JOptionPane.showMessageDialog(this, "Enter a task before adding.", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Task addedTask = taskManager.getTasks().get(taskManager.getTasks().size() - 1);
        listModel.addElement(addedTask);
        taskInput.setText("");
        taskInput.requestFocusInWindow();
        updateStatus();
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Select a task to delete.", "No Selection", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        taskManager.deleteTask(selectedIndex);
        listModel.remove(selectedIndex);
        updateStatus();
    }

    private void toggleSelectedTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Select a task to mark complete/undo.", "No Selection", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        taskManager.toggleTaskCompletion(selectedIndex);
        taskList.repaint();
        updateStatus();
    }

    private void clearCompletedTasks() {
        int removedCount = taskManager.clearCompleted();
        if (removedCount == 0) {
            JOptionPane.showMessageDialog(this, "No completed tasks to clear.", "Nothing to Clear", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        refreshTaskList();
        updateStatus();
    }

    private void refreshTaskList() {
        listModel.clear();
        for (Task task : taskManager.getTasks()) {
            listModel.addElement(task);
        }
    }

    private void updateStatus() {
        statusLabel.setText(String.format("Total: %d  Completed: %d  Pending: %d",
                taskManager.getTasks().size(),
                taskManager.getCompletedCount(),
                taskManager.getPendingCount()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoApp().setVisible(true));
    }
}

