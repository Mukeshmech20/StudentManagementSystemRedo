package repository;

import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStudentRepository implements StudentRepository {

    private final String fileName;

    public FileStudentRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Student> loadStudents() {

        List<Student> students = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                if (data.length != 4) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    String course = data[2].trim();
                    int age = Integer.parseInt(data[3].trim());

                    students.add(new Student(id, name, course, age));

                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid student record: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }

        return students;
    }

    @Override
    public void saveStudents(List<Student> students) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (Student student : students) {

                writer.write(
                    student.getId() + "," +
                    student.getName() + "," +
                    student.getCourse() + "," +
                    student.getAge()
                );

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }
}