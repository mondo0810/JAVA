package employeapp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Application {

    private static EmployeeImpl employeeImpl;

    public static void Menu() {
        System.out.println("Welcome to Aptech Bank Online");
        System.out.println("1. Create a new Customer");
        System.out.println("2. Update Customer");
        System.out.println("3. Delete Customer");
        System.out.println("4. Find Customer by ID");
        System.out.println("5. Display all customer information");
        System.out.println("6. Create table Employee");
        System.out.println("7. Exit");
    }

    public static void main(String[] args) {
        MySQLConnection mySQLConnection = new MySQLConnection();
        Connection conn = mySQLConnection.connect();
        if (conn == null) {
            System.out.println("Failed to connect to the database.");
            return;
        }

        employeeImpl = new EmployeeImpl(conn);

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
                        mySQLConnection.createEmployeeTable();
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } while (choice != 7);

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createCustomer(Scanner scanner) throws SQLException {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter customer salary: ");
        String salary = scanner.nextLine();

        Employee employee = new Employee(0, name, salary);
        employeeImpl.addEmployee(employee);
        System.out.println("Customer created successfully.");
    }

    private static void updateCustomer(Scanner scanner) throws SQLException {
        System.out.print("Enter customer ID to update: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new salary: ");
        String salary = scanner.nextLine();

        Employee updatedEmployee = new Employee(customerId, name, salary);
        employeeImpl.updateEmployee(customerId, updatedEmployee);
        System.out.println("Customer updated successfully.");
    }

    private static void deleteCustomer(Scanner scanner) throws SQLException {
        System.out.print("Enter customer ID to delete: ");
        int customerId = scanner.nextInt();
        employeeImpl.delEmployee(customerId);
        System.out.println("Customer deleted successfully.");
    }

    private static void findCustomerById(Scanner scanner) throws SQLException {
        System.out.print("Enter customer ID to find: ");
        int customerId = scanner.nextInt();
        Employee employee = employeeImpl.getEmployeeById(customerId);
        if (employee != null) {
            System.out.println("Customer found: " + employee.getName() + employee.getSalary());
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void displayAllCustomers() throws SQLException {
        System.out.println("All customers:");
        List<Employee> allEmployees = employeeImpl.getAllEmployee();
//        allCustomers.forEach(customer ->{
//            System.out.println("Customer ID: " + customer.getId() + ", Name: " + customer.getName());
//        });
        for (Employee cust : allEmployees) {
            System.out.println("Customer ID: " + cust.getId() + ", Name: " + cust.getName() + ", Salary: " + cust.getSalary());
        }
    }
}
