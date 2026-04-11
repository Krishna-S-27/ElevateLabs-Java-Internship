# Task 2 - Student Record Management System

CLI-based Java application to manage student records using CRUD operations.

## Features
- Add student
- View all students
- Update student by ID
- Delete student by ID
- Input validation for numbers and empty values

## Class Structure
- `Student.java`: Data model for a student record
- `StudentManager.java`: Handles in-memory CRUD operations
- `StudentRecordManagement.java`: Menu-driven CLI runner

## How to Run
From project root (`D:\ElevateLabs-Java-Internship`):

```powershell
javac -d out src\Task2\*.java
java -cp out Task2.StudentRecordManagement
```

