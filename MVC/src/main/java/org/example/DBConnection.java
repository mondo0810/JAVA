package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection createConnection() {
        String dbUrl = "jdbc:mysql://localhost:3308/java";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, "root", "");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}
