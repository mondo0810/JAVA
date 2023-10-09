package lab3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student implements Serializable {
    private String name;
    private int age;
    private double mark;

    public Student(String name, int age, double mark) {
        this.name = name;
        this.age = age;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", mark=" + mark +
                '}';
    }
}

public class StudentManager {
    private static final String FILE_NAME = "students.txt";
    private List<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public void addStudent(String name, int age, double mark) {
        Student student = new Student(name, age, mark);
        students.add(student);
    }

    public void saveToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(FILE_NAME)))) {
            for (Student student : students) {
                outputStream.writeObject(student);
            }
            System.out.println("Student information saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(FILE_NAME)))) {
            Student student;
            while ((student = (Student) inputStream.readObject()) != null) {
                System.out.println(student);
            }
        } catch (EOFException e) {
            // End of file reached, do nothing
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu -------------------------------------------------");
            System.out.println("1. Add a list of Students and save to File");
            System.out.println("2. Loading list of Students from a File");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter student mark: ");
                    double mark = scanner.nextDouble();
                    studentManager.addStudent(name, age, mark);
                    break;
                case 2:
                    studentManager.loadFromFile();
                    break;
                case 3:
                    studentManager.saveToFile();
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
