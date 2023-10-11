package org.example.test;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.Scanner;


/*
 * 1. Statement: Kem an toan do viec thuc hien truc tiep Sql engine thong qua tham so va table
 * 2. Preparedstatement: An toan, do viec truyen cac tham so duocj thuc hien thong qua "check dau vao"
 * 3. CallableStatement: An toan, su dung Proceduce(thu tuc luu aka ham trong sql) cua SQL
 * SQLInjection: Loi xay ra voi sql khi thuc hien ket noi sql
 */
public class Main {

    public static void crudMySQLDb() throws SQLException {
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();

        // Tao statement object de thuc thi
        Statement stm = conn.createStatement();
//        stm.execute("Drop table if exists product");
//        stm.execute("create table  product(id int primary key, proName varchar(50), description varchar(100))");
//        stm.execute("INSERT INTO user VALUES ('iphone', 'dssdds')");
        ResultSet rs = stm.executeQuery("select * from user");
        while (rs.next()) {
            System.out.println("User Name: " + rs.getString("username"));
        }
        rs.close();
        stm.close();
        conn.close();
    }

    public static void menu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        System.out.println("1. LoginWithStatement");
        System.out.println("2. LoginWithPreparedStatement");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        choice = sc.nextInt();
        sc.nextLine();  // Consume the newline after reading the choice

        if (choice == 1 || choice == 2) {
            System.out.print("Enter username: ");
            String username = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            switch (choice) {
                case 1:
                    loginWithStatement(username, password);
                    break;
                case 2:
                    loginWithPrepare(username, password);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } else if (choice == 3) {
            System.out.println("Exiting...");
        } else {
            // Invalid choice
            System.out.println("Invalid choice.");
        }
    }



    // login with statement
    public static void loginWithStatement(String username, String password) throws SQLException {
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();
        Statement stm = conn.createStatement();

        ResultSet rs = stm.executeQuery("select username,email from user where username = '" + username + "' and password = '" + password + "'");
        while (rs.next()) {
            System.out.println("User Name: " + rs.getString("username") + rs.getString("email"));
        }
    }

    // login with PreparedStatement
    public static void loginWithPrepare(String username, String password) throws SQLException {
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();
        String query = "select username,email from user where username = ? and password = ?";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            System.out.println("User Name: " + rs.getString("username") + rs.getString("email"));
        }
    }

    public static void main(String[] args) throws SQLException {
        menu();
//        crudMySQLDb();


//        ConnectJDBC connectJDBC = new ConnectJDBC();
//        Connection conn = connectJDBC.connect();

//        String query = "SELECT * FROM user";
//
//        Statement stm = null;
//        try {
//            //Tạo đối tượng Statement
//            stm = conn.createStatement();
//
//            //Thực thi truy vấn và trả về đối tượng ResultSet
//            ResultSet rs = stm.executeQuery(query);
//
//            //Duyệt kết quả trả về
//            while (rs.next()) {  //Di chuyển con trỏ xuống bản ghi kế tiếp
//                int id = rs.getInt("id");
//                String username = rs.getString("username");
//                String password = rs.getString("password");
//                String email = rs.getString("email");
//
//                System.out.println(id + " - " + username + " - " + password + " - " + email);
//            }
//            //Đóng kết nối
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}