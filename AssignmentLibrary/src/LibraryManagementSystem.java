import controller.BookController;
import service.BookModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        BookController bookController = new BookController(new BookModel(), new BookView());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. View All Books");
            System.out.println("2. Search Books");
            System.out.println("3. View Book Details");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    bookController.getAllBooks();
                    break;
                case 2:
                    System.out.print("Enter book code (or leave empty): ");
                    String code = scanner.nextLine();
                    System.out.print("Enter book name (or leave empty): ");
                    String name = scanner.nextLine();
                    bookController.searchBooks(code, name);
                    break;
                case 3:
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    bookController.viewBookDetails(bookId);
                    break;
                case 4:
                    System.out.print("Enter book ID to borrow: ");
                    int borrowBookId = scanner.nextInt();
                    bookController.borrowBook(borrowBookId);
                    break;
                case 5:
                    System.out.print("Enter book ID to return: ");
                    int returnBookId = scanner.nextInt();
                    bookController.returnBook(returnBookId);
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
