package employeapp;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    public  Employee getEmployeeById(int Id) throws SQLException;
    public List<Employee> getAllEmployee() throws SQLException;
    public Employee addEmployee(Employee e) throws SQLException;
    public Employee delEmployee(int Id) throws SQLException;
    public Employee updateEmployee(int Id, Employee e) throws SQLException;

}
