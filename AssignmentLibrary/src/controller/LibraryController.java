package controller;

import java.sql.Connection;

public class LibraryController {
    public static void main(String[] args) {
        // Thiết lập kết nối đến cơ sở dữ liệu
        Connection connection = DatabaseConnection.connect();

        // Tạo một đối tượng service và gọi các phương thức để thực hiện các tính năng
        LibraryService libraryService = new LibraryService(connection);
        libraryService.viewAllBooks();
        libraryService.viewAllStudents();

        // Đóng kết nối đến cơ sở dữ liệu
        DatabaseConnection.close(connection);
    }
}
