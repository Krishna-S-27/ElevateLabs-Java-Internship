# Task 6: Java GUI - ToDo App

## Objective
Build a desktop To-Do list app using Java Swing.

## Tools
- Java
- Swing (built-in)
- IntelliJ CE or Eclipse

## Deliverables
- `ToDoApp.java`: GUI app built with `JFrame`, `JButton`, and `JTextField`
- `Task.java`: task model with completed state
- `TaskManager.java`: in-memory add/delete/complete task operations
- `TaskManagerSmokeTest.java`: quick logic test for add/delete/complete flows

## Features
- Add tasks using text input + **Add Task** button (or press Enter)
- Delete selected tasks using **Delete Task** button
- Mark selected tasks as complete/undo using **Complete / Undo**
- Clear all completed tasks using **Clear Completed**
- Live task summary: total, completed, pending
- Validation for empty tasks, delete without selection, and clear with nothing completed

## How to Compile and Run
From project root (`D:\ElevateLabs-Java-Internship`):

```powershell
javac -d out src\Task6\*.java
```

Run the GUI:

```powershell
java -cp out Task6.ToDoApp
```

Run the smoke test:

```powershell
java -cp out Task6.TaskManagerSmokeTest
```

