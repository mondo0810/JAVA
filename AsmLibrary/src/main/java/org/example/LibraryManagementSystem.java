package org.example;

import org.example.controller.BookController;
import org.example.service.BookService;
import org.example.view.BookView;

import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        BookController bookController = new BookController(new BookService(), new BookView());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Aptech Library");
            System.out.println("1. View All Books In Library");
            System.out.println("2. Search Books");
            System.out.println("3. View Book Details");
            System.out.println("4. Student Borrow Book");
            System.out.println("5. Student Return Book");
            System.out.println("6. Get All Borrow History");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    bookController.getAllBooks();
                    break;
                case 2:
                    bookController.getAllBooks();
                    System.out.print("Enter book code (eg:B003 or leave empty) : ");
                    String code = scanner.nextLine();
                    System.out.print("Enter book name (eg:Book C or leave empty): ");
                    String name = scanner.nextLine();
                    bookController.searchBooks(code, name);
                    break;
                case 3:
                    bookController.getAllBooks();
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    bookController.viewBookDetails(bookId);
                  break;
                case 4:
                    bookController.getAllBooks();
                    System.out.print("Enter book ID to borrow: ");
                    int borrowBookId = scanner.nextInt();
                    System.out.print("Enter student ID: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter due date (yyyy-MM-dd): ");
                    String dueDate = scanner.nextLine();
                    bookController.borrowBook(borrowBookId, studentId, dueDate);
                    break;
                case 5:
                    bookController.getAllBooks();
                    System.out.print("Enter book ID to return: ");
                    int returnBookId = scanner.nextInt();
                    System.out.print("Enter student ID to return: ");
                    scanner.nextLine();
                    int returnStudentId = scanner.nextInt();
                    bookController.returnBook(returnBookId, returnStudentId);
                    break;
                case 6:
                    bookController.getAllBorrowHistory();
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
