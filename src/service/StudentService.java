package service;

import model.Student;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final StudentRepository repository;
    private final ArrayList<Student> students;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
        this.students = new ArrayList<>(repository.loadStudents());
    }

    // Create
    public boolean addStudent(Student student) {

        if (findStudentById(student.getId()) != null) {
            return false;
        }

        students.add(student);
        repository.saveStudents(students);
        return true;
    }

    // Read
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // Update
    public boolean updateStudent(int id, String name, String course, int age) {

        Student student = findStudentById(id);

        if (student == null) {
            return false;
        }

        student.setName(name);
        student.setCourse(course);
        student.setAge(age);

        repository.saveStudents(students);
        return true;
    }

    // Delete
    public boolean deleteStudent(int id) {

        Student student = findStudentById(id);

        if (student == null) {
            return false;
        }

        students.remove(student);
        repository.saveStudents(students);
        return true;
    }

    // Find
    public Student findStudentById(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }
}