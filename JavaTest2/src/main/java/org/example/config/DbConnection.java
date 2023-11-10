package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    private static final String HOST_NAME = "localhost:3308";
    private static final String DB_NAME = "testjava2";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static final String CONNECTION_URL = "jdbc:mysql://" + HOST_NAME + "/" + DB_NAME;

    public Connection connect() {
        try {
            Properties properties = new Properties();
            properties.put("user", USERNAME);
            properties.put("password", PASSWORD);

            try (Connection conn = DriverManager.getConnection(CONNECTION_URL, properties)) {
                System.out.println("Kết nối thành công");
                return conn;
            }
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    private void handleSQLException(SQLException e) {
        System.err.println("Lỗi kết nối cơ sở dữ liệu:");
        e.printStackTrace();

    }

    public static void main(String[] args) {
        DbConnection connector = new DbConnection();
        Connection connection = connector.connect();

        if (connection != null) {
            // Thực hiện các thao tác sử dụng connection ở đây
            // Ví dụ: PreparedStatement, Statement, ResultSet, và các thao tác khác
        } else {
            System.out.println("Không thể kết nối đến cơ sở dữ liệu");
        }
    }
}
