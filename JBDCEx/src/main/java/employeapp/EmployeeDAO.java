package employeapp;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO<T> {
    public  T getEmployeeById(int Id) throws SQLException;
    public List<T> getAllEmployee() throws SQLException;
    public T addEmployee(T t) throws SQLException;
    public T delEmployee(int Id) throws SQLException;
    public T updateEmployee(int Id, T t) throws SQLException;

}
