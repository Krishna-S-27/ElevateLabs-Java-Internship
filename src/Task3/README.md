# Task 3 - Library Management System

CLI-based Java mini system to manage books and users with issue/return workflow.

## Features
- Add, view, and remove books
- Add, view, and remove users
- Issue book to user
- Return issued book
- Input validation for numeric and empty values
- Basic business checks (cannot remove issued book or user with active issued books)

## Class Structure
- `Book.java`: Book model with issue status and borrower tracking
- `User.java`: User model
- `BookManager.java`: In-memory CRUD operations for books
- `UserManager.java`: In-memory CRUD operations for users
- `LibraryService.java`: Business rules for issue/return and safe removals
- `LibraryManagementSystem.java`: Menu-driven CLI runner

## How to Run
From project root (`D:\ElevateLabs-Java-Internship`):

```powershell
javac -d out src\Task3\*.java
java -cp out Task3.LibraryManagementSystem
```

