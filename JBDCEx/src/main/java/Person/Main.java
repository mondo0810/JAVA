package Person;

import org.example.test.ConnectJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static CustomerDAO customerDAO;

    public static void Menu() {
        System.out.println("Welcome to Aptech Bank Online");
        System.out.println("1. Create a new Customer");
        System.out.println("2. Update Customer");
        System.out.println("3. Delete Customer");
        System.out.println("4. Find Customer by ID");
        System.out.println("5. Display all customer information");
        System.out.println("6. Exit");
    }

    public static void main(String[] args) {
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection connection = connectJDBC.connect();
        if (connection == null) {
            System.out.println("Failed to connect to the database.");
            return;
        }

        customerDAO = new CustomerDAO(connection);

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            Menu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        createCustomer(scanner);
                        break;
                    case 2:
                        updateCustomer(scanner);
                        break;
                    case 3:
                        deleteCustomer(scanner);
                        break;
                    case 4:
                        findCustomerById(scanner);
                        break;
                    case 5:
                        displayAllCustomers();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } while (choice != 6);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createCustomer(Scanner scanner) throws SQLException {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        Customer customer = new Customer(0, name, email);
        customerDAO.createCustomer(customer);
        System.out.println("Customer created successfully.");
    }

    private static void updateCustomer(Scanner scanner) throws SQLException {
        System.out.print("Enter customer ID to update: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new email: ");
        String email = scanner.nextLine();

        Customer updatedCustomer = new Customer(customerId, name, email);
        customerDAO.updateCustomer(customerId, updatedCustomer);
        System.out.println("Customer updated successfully.");
    }

    private static void deleteCustomer(Scanner scanner) throws SQLException {
        System.out.print("Enter customer ID to delete: ");
        int customerId = scanner.nextInt();
        customerDAO.deleteCustomer(customerId);
        System.out.println("Customer deleted successfully.");
    }

    private static void findCustomerById(Scanner scanner) throws SQLException {
        System.out.print("Enter customer ID to find: ");
        int customerId = scanner.nextInt();
        Customer customer = customerDAO.getCustomerById(customerId);
        if (customer != null) {
            System.out.println("Customer found: " + customer.getName());
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void displayAllCustomers() throws SQLException {
        System.out.println("All customers:");
        List<Customer> allCustomers = customerDAO.getAllCustomers();
        for (Customer cust : allCustomers) {
            System.out.println("Customer ID: " + cust.getId() + ", Name: " + cust.getName() + ", Email: " + cust.getPassword());
        }
    }
}
