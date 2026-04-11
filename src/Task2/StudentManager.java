package Task2;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentManager {
    private final Map<Integer, Student> records = new LinkedHashMap<>();

    public boolean addStudent(Student student) {
        if (records.containsKey(student.getId())) {
            return false;
        }
        records.put(student.getId(), student);
        return true;
    }

    public Student getStudentById(int id) {
        return records.get(id);
    }

    public boolean updateStudent(int id, String newName, int newAge, String newGrade) {
        Student existing = records.get(id);
        if (existing == null) {
            return false;
        }

        existing.setName(newName);
        existing.setAge(newAge);
        existing.setGrade(newGrade);
        return true;
    }

    public boolean deleteStudent(int id) {
        return records.remove(id) != null;
    }

    public Collection<Student> getAllStudents() {
        return records.values();
    }

    public boolean isEmpty() {
        return records.isEmpty();
    }
}

