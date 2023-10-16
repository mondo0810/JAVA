package employeapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeImpl implements EmployeeDAO<Employee> {

    private Connection conn;

    public EmployeeImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Employee getEmployeeById(int Id) throws SQLException {
        String query = "SELECT id, name, salary FROM employee WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, Id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String salary = resultSet.getString("salary");

                return new Employee(id, name,salary);
            }
            return null;
        }
    }

    @Override
    public List<Employee>  getAllEmployee() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT id, name, salary FROM employee";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String salary = resultSet.getString("salary");

                employees.add(new Employee(id, name,salary));
            }
        }
        return employees;
    }

    @Override
    public Employee addEmployee(Employee e) throws SQLException {
        String query = "INSERT INTO employee (name, salary) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, e.getName());
            preparedStatement.setString(2, e.getSalary());
            preparedStatement.executeUpdate();
        }
        return null;
    }

    @Override
    public Employee delEmployee(int Id) throws SQLException {
        String query = "DELETE FROM employee WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, Id);
            preparedStatement.executeUpdate();
        }
        return null;
    }

    @Override
    public Employee updateEmployee(int Id, Employee updatedEmployee) throws SQLException {
        String query = "UPDATE employee SET name = ?, salary = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, updatedEmployee.getName());
            preparedStatement.setString(2, updatedEmployee.getSalary());
            preparedStatement.setInt(3, Id);
            preparedStatement.executeUpdate();
        }
        return null;
    }
}
