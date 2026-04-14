# Task 4: Java File I/O - Notes App

## Objective
Create a text-based notes manager that performs file read/write operations using Java `FileReader` and `FileWriter`.

## Deliverable
- `NotesApp.java`: menu-driven CLI app to add, view, search, and delete notes.
- Notes are persisted in `src/Task4/notes.txt`.

## Features
- Add note (title + content)
- View all saved notes
- Search notes by keyword
- Delete a note by ID
- Auto-save notes after changes and on exit

## Tech Used
- Java
- VS Code / Terminal
- Java File I/O (`FileReader`, `FileWriter`, `BufferedReader`, `BufferedWriter`)

## Run
From project root (`D:\ElevateLabs-Java-Internship`):

```powershell
javac -d out src\Task4\NotesApp.java
java -cp out Task4.NotesApp
```

