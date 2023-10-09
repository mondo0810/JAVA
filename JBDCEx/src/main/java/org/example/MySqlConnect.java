package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnect {
    public static Connection getConnection() throws SQLException {
        String hostname = "localhost:3008";
        String dbName = "java";
        String userName = "root";
        String password = "";
        String connectionURL = "jdbc:mysql://"+hostname+"/"+dbName;
             Connection connection = null;

        try {
            connection = DriverManager.getConnection(connectionURL, userName, password);
            System.out.println("Kết nối thành công");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static  void createUser(String username, String password) throws SQLException{
        Connection connection =getConnection();
        // Tao statement thuc thi
        Statement statement = connection.createStatement();
        String query = "insert into user(username, password)" + " VALUES('Vinh','hahha')";
        statement.executeUpdate(query);
        int count = statement.executeUpdate(query);
        System.out.println("Count " + count);

    }



    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("Failed to connect");
            createUser("dssdsd","sdsd");
        } else {
            System.out.println("Success");
        }
    }



}
