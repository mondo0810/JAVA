package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public void getAllStudents() {
        // Thực hiện truy vấn SQL để lấy tất cả sinh viên từ cơ sở dữ liệu
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Students");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String studentName = resultSet.getString("student_name");
                String studentEmail = resultSet.getString("student_email");

                // Xử lý kết quả (ví dụ: in ra màn hình hoặc trả lại cho controller)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Các phương thức khác cho truy vấn sinh viên, cập nhật thông tin, tìm kiếm, và thêm mới
}
