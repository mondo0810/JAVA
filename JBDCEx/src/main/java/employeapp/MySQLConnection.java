package employeapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLConnection {
    private String hostName = "localhost:3308";
    private String dbName = "java";
    private String username = "root";
    private String password = "";

    private String connectionURL = "jdbc:mysql://"+hostName+"/"+dbName;

    public Connection connect(){
        //Tạo đối tượng Connection
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(connectionURL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public String createEmployeeTable() {
        MySQLConnection mySQLConnection = new MySQLConnection();
        Connection conn = mySQLConnection.connect();

        try (PreparedStatement dropTable = conn.prepareStatement("DROP TABLE IF EXISTS employee");
             PreparedStatement createTable = conn.prepareStatement("CREATE TABLE employee(id int primary key AUTO_INCREMENT, name varchar(255), salary varchar(255))")) {

            dropTable.execute();
            createTable.execute();

            System.out.println("Created table employee");
            return "Ok";
        } catch (SQLException e) {
            System.err.println("Error: Unable to create employee table - " + e.getMessage());
            return "Error: Unable to create employee table";
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}