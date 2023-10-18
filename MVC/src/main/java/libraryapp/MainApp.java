package libraryapp;

import java.sql.SQLException;
import java.util.Scanner;


public class MainApp {
    public static void Menu() {
        System.out.println("Welcome to LibraryApp");
        System.out.println("1. Add book to library");
        System.out.println("2. Search book");
        System.out.println("3. Borrow book to student");
        System.out.println("4. Displays a list of borrowed students by studentid");
        System.out.println("5. All book in library");
        System.out.println("6. Exit");
    }

    public static void main(String[] args) {
        Library library = null;
        try {
            library = new Library();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        BookController bookController = new BookController(library);
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            Menu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    searchBook(scanner);
                    break;
                case 3:
                    borrowBook(scanner);
                    break;
                case 4:
                    searchBookByStudentId(scanner);
                    break;
                case 5:
                    getAllBook();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);
    }

    public static void addBook(Scanner scanner){}
    public static void borrowBook(Scanner scanner){}
    public static void searchBook(Scanner scanner){}
    public static void searchBookByStudentId(Scanner scanner){}
    public static void getAllBook(){}

}
