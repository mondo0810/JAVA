import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        try {
            // Establish a connection to the MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/aptechlibrary_db", "your_username", "your_password");

            while (true) {
                System.out.println("Library Management System");
                System.out.println("1. View all books");
                System.out.println("2. Search books by code or name");
                System.out.println("3. Update book status");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        viewAllBooks(connection);
                        break;
                    case 2:
                        searchBooks(connection, scanner);
                        break;
                    case 3:
                        updateBookStatus(connection, scanner);
                        break;
                    case 0:
                        System.out.println("Exiting the Library Management System.");
                        connection.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void viewAllBooks(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Books");

            System.out.println("Books in the library:");
            while (resultSet.next()) {
                int code = resultSet.getInt("code");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                boolean isBorrowed = resultSet.getBoolean("is_borrowed");
                System.out.println("Code: " + code + ", Name: " + name + ", Author: " + author + ", Borrowed: " + (isBorrowed ? "Yes" : "No"));
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void searchBooks(Connection connection, Scanner scanner) {
        System.out.print("Enter code or name to search: ");
        String query = scanner.next();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Books WHERE code = '" + query + "' OR name LIKE '%" + query + "%'");

            System.out.println("Search results:");
            while (resultSet.next()) {
                int code = resultSet.getInt("code");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                boolean isBorrowed = resultSet.getBoolean("is_borrowed");
                System.out.println("Code: " + code + ", Name: " + name + ", Author: " + author + ", Borrowed: " + (isBorrowed ? "Yes" : "No"));
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateBookStatus(Connection connection, Scanner scanner) {
        System.out.print("Enter book code to update status: ");
        int code = scanner.nextInt();
        System.out.print("Enter new status (0 for not borrowed, 1 for borrowed): ");
        int newStatus = scanner.nextInt();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE Books SET is_borrowed = " + newStatus + " WHERE code = " + code);
            System.out.println("Book status updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
