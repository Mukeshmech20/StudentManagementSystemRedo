public class Main {
    
}
import model.Student;
import repository.FileStudentRepository;
import repository.StudentRepository;
import service.StudentService;
import util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        StudentRepository repository =
                new FileStudentRepository("students.txt");

        StudentService studentService =
                new StudentService(repository);

        while (true) {

            displayMenu();

            int choice = readInteger("Enter your choice: ");

            switch (choice) {

                case 1:
                    addStudent(studentService);
                    break;

                case 2:
                    viewStudents(studentService);
                    break;

                case 3:
                    updateStudent(studentService);
                    break;

                case 4:
                    deleteStudent(studentService);
                    break;

                case 5:
                    System.out.println(
                            "Thank you for using Student Management System!"
                    );
                    scanner.close();
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please enter a number from 1 to 5."
                    );
            }
        }
    }

    private static void displayMenu() {

        System.out.println("\n==================================");
        System.out.println("     STUDENT MANAGEMENT SYSTEM");
        System.out.println("==================================");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Exit");
        System.out.println("==================================");
    }

    private static void addStudent(StudentService studentService) {

        System.out.println("\n----------- ADD STUDENT -----------");

        int id = readPositiveInteger("Enter Student ID: ");

        if (studentService.findStudentById(id) != null) {
            System.out.println("Student ID already exists!");
            return;
        }

        String name = readValidName();

        String course = readValidCourse();

        int age = readValidAge();

        Student student = new Student(id, name, course, age);

        if (studentService.addStudent(student)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Unable to add student.");
        }
    }

    private static void viewStudents(StudentService studentService) {

        System.out.println("\n----------- STUDENT RECORDS -----------");

        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }

        System.out.println("---------------------------------------");
    }

    private static void updateStudent(StudentService studentService) {

        System.out.println("\n----------- UPDATE STUDENT -----------");

        int id = readPositiveInteger("Enter Student ID to update: ");

        if (studentService.findStudentById(id) == null) {
            System.out.println("Student not found!");
            return;
        }

        String name = readValidName();

        String course = readValidCourse();

        int age = readValidAge();

        if (studentService.updateStudent(id, name, course, age)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Unable to update student.");
        }
    }

    private static void deleteStudent(StudentService studentService) {

        System.out.println("\n----------- DELETE STUDENT -----------");

        int id = readPositiveInteger("Enter Student ID to delete: ");

        if (studentService.deleteStudent(id)) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static int readInteger(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static int readPositiveInteger(String message) {

        while (true) {

            int value = readInteger(message);

            if (InputValidator.isValidId(value)) {
                return value;
            }

            System.out.println("ID must be greater than 0.");
        }
    }

    private static int readValidAge() {

        while (true) {

            int age = readInteger("Enter Age: ");

            if (InputValidator.isValidAge(age)) {
                return age;
            }

            System.out.println("Age must be between 1 and 120.");
        }
    }

  private static String readValidName() {

    while (true) {

        System.out.print("Enter Student Name: ");

        if (!scanner.hasNextLine()) {
            System.out.println("Input ended. Exiting program.");
            System.exit(0);
        }

        String name = scanner.nextLine().trim();

        if (InputValidator.isValidName(name)) {
            return name;
        }

        System.out.println(
                "Invalid name. Please enter letters and spaces only."
        );
    }
}

    private static String readValidCourse() {

        while (true) {

            System.out.print("Enter Course: ");

            String course = scanner.nextLine().trim();

            if (InputValidator.isValidCourse(course)) {
                return course;
            }

            System.out.println(
                    "Invalid course. Please enter a valid course name."
            );
        }
    }
}
    
}
