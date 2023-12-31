package model;

import entity.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class CustomException extends Exception {
    private static final long serialVersionUID = 1L;

    public CustomException(String str) {
        super(str);
    }
}

public class EmployeeSystem {
    public static Map<Integer, Employee> map = new HashMap<>();

    public static void addEmployee(String name, int age, int employeeId) throws CustomException {
        try {
            Employee emp = new Employee(name, age, employeeId);
            map.put(employeeId, emp);
            System.out.println("Employee added successfully.");
        }
        catch (Exception e) {
            throw new CustomException("Invalid employee data: " + e.getMessage());
        }
        operations();
    }

    public static void deleteEmployee(int empId) throws CustomException {
        if (map.containsKey(empId)) {
            map.remove(empId);
            System.out.println("Employee with ID " + empId + " has been deleted.");
        } else {
            throw new CustomException("Employee with ID " + empId + " not found.");
        }
        operations();
    }

    public static void searchEmployee(int empId) throws CustomException {
        if (map.containsKey(empId)) {
            System.out.println("Employee Details: " + map.get(empId));
        } else {
            throw new CustomException("Employee with ID " + empId + " not found.");
        }
        operations();
    }

    public static void listEmployees() throws CustomException {
        if (map.isEmpty()) {
            throw new CustomException("No employees found.");
        }
        System.out.println(map.toString());
        operations();
    }

    public static void operations() {
        try {
            System.out.println("\n***** Employee management system ******");
            System.out.println("1. Add Employee");
            System.out.println("2. Delete Employee");
            System.out.println("3. Search Employee");
            System.out.println("4. Employee List");
            System.out.println("5. Exit");

            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextInt()) { // Kiểm tra xem người dùng đã nhập một số nguyên hay chưa
                int userInput = scanner.nextInt();

                switch (userInput) {
                    case 1:
                        System.out.println("Enter Employee Details (Name, age, id)");
                        Scanner scanner1 = new Scanner(System.in);

                        String name = scanner1.next();
                        int age = scanner1.nextInt();
                        int id = scanner1.nextInt();

                        if (!name.isEmpty() && age != 0 && id != 0) {
                            addEmployee(name, age, id);
                        } else {
                            throw new CustomException("Invalid input for adding employee.");
                        }
                        break;
                    case 2:
                        System.out.println("Enter Employee ID to delete:");
                        int empIdToDelete = scanner.nextInt();
                        deleteEmployee(empIdToDelete);
                        break;
                    case 3:
                        System.out.println("Enter Employee ID to search:");
                        int empIdToSearch = scanner.nextInt();
                        searchEmployee(empIdToSearch);
                        break;
                    case 4:
                        listEmployees();
                        break;
                    case 5:
                        System.out.println("Exiting Employee Management System.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        operations();
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid option.");
                operations();
            }
        } catch (CustomException e) {
            System.out.println("Error: " + e.getMessage());
            operations();
        }
    }


    public static void main(String[] args) {
        operations();
    }
}
