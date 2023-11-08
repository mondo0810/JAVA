package org.example.dao;

import org.example.config.DatabaseConnection;
import org.example.entity.Book;
import org.example.entity.BorrowHistory;
import org.example.entity.BorrowTicket;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookDAO {
    private Connection connection;

    public BookDAO() {
        this.connection = DatabaseConnection.connect();
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int book_id = resultSet.getInt("book_id");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                boolean isBorrowed = resultSet.getBoolean("is_borrowed");

                Book book = new Book(book_id, code, name, author, isBorrowed);
                books.add(book);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public List<Book> searchBooks(String code, String name) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE code = ? OR name LIKE ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, "%" + name + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int book_id = resultSet.getInt("book_id");
                String bookCode = resultSet.getString("code");
                String bookName = resultSet.getString("name");
                String author = resultSet.getString("author");
                boolean isBorrowed = resultSet.getBoolean("is_borrowed");

                Book book = new Book(book_id, bookCode, bookName, author, isBorrowed);
                books.add(book);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }


    public Book getBookById(int bookId) {
        String query = "SELECT * FROM books WHERE book_id = ?";
        Book book = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int book_id = resultSet.getInt("book_id");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                boolean isBorrowed = resultSet.getBoolean("is_borrowed");

                book = new Book(book_id, code, name, author, isBorrowed);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public boolean updateBookStatus(Book book) {
        String updateQuery = "UPDATE books SET is_borrowed = ? WHERE book_id = ?";
        boolean success = false;

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setBoolean(1, book.isIs_borrowed());
            preparedStatement.setInt(2, book.getBook_id());

            int rowsUpdated = preparedStatement.executeUpdate();
            success = rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean borrowBook(BorrowTicket borrowTicket, BorrowHistory borrowHistory) {
        Connection connection = DatabaseConnection.connect();

        // Thực hiện cập nhật bảng borrowtickets
        String ticketInsertQuery = "INSERT INTO borrowtickets (student_id, due_date) VALUES (?, ?)";
        try (PreparedStatement ticketStatement = connection.prepareStatement(ticketInsertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ticketStatement.setInt(1, borrowTicket.getStudent_id());
            ticketStatement.setString(2, borrowTicket.getDue_date());

            int rowsInserted = ticketStatement.executeUpdate();

            if (rowsInserted <= 0) {
                connection.rollback();
                return false;
            }

            try (ResultSet generatedKeys = ticketStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    borrowTicket.setTicket_id(generatedKeys.getInt(1));
                } else {
                    connection.rollback();
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        // Thực hiện cập nhật bảng borrowhistory
        String historyInsertQuery = "INSERT INTO borrowhistory (book_id, ticket_id, is_returned) VALUES (?, ?, false)";
        try (PreparedStatement historyStatement = connection.prepareStatement(historyInsertQuery)) {
            historyStatement.setInt(1, borrowHistory.getBook_id());
            historyStatement.setInt(2, borrowTicket.getTicket_id());

            int rowsInserted = historyStatement.executeUpdate();

            if (rowsInserted <= 0) {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        DatabaseConnection.close(connection);

        return true;
    }


    public boolean returnBook(int bookId, int studentId) {
        Connection connection = DatabaseConnection.connect();

        // Thực hiện cập nhật bảng borrowhistory để đánh dấu sách đã trả
        String historyUpdateQuery = "UPDATE borrowhistory AS bh\n" +
                "LEFT JOIN borrowtickets AS bt ON bh.ticket_id = bt.ticket_id\n" +
                "SET bh.is_returned = true\n" +
                "WHERE bt.student_id = ? AND bh.book_id = ? AND bh.is_returned = 0\n";
        try (PreparedStatement historyUpdateStatement = connection.prepareStatement(historyUpdateQuery)) {
            historyUpdateStatement.setInt(1, bookId);
            historyUpdateStatement.setInt(2, studentId);

            int rowsUpdated = historyUpdateStatement.executeUpdate();

            if (rowsUpdated <= 0) {
                connection.rollback(); // Lỗi, hoàn tác giao dịch
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        // Sử dụng phương thức updateBookStatus để cập nhật trạng thái sách đã trả
        Book book = new Book();
        book.setBook_id(bookId);
        book.setIs_borrowed(false);

        boolean bookUpdateSuccess = updateBookStatus(book);

        if (!bookUpdateSuccess) {
            try {
                connection.rollback(); // Lỗi, hoàn tác giao dịch
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return false;
        }

        DatabaseConnection.close(connection);

        return true;
    }


    public List<Map<String, Object>> getAllBorrowHistory() {
        List<Map<String, Object>> borrowHistories = new ArrayList<>();
        Connection connection = DatabaseConnection.connect();

        String query = "SELECT " +
                "s.student_id, s.student_name, " +
                "bt.ticket_id, bt.borrow_date, bt.due_date, " +
                "bh.borrow_id, bh.book_id, bh.is_returned, " +
                "b.code, b.name, b.author " +
                "FROM students AS s " +
                "LEFT JOIN borrowtickets AS bt ON s.student_id = bt.student_id " +
                "LEFT JOIN borrowhistory AS bh ON bt.ticket_id = bh.ticket_id " +
                "LEFT JOIN books AS b ON bh.book_id = b.book_id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> borrowHistory = new HashMap<>();
                borrowHistory.put("ticket_id", resultSet.getInt("ticket_id"));
                borrowHistory.put("student_name", resultSet.getString("student_name"));
                borrowHistory.put("code", resultSet.getString("code"));
                borrowHistory.put("name", resultSet.getString("name"));
                borrowHistory.put("author", resultSet.getString("author"));
                borrowHistory.put("borrow_date", resultSet.getString("borrow_date"));
                borrowHistory.put("due_date", resultSet.getString("due_date"));
                borrowHistory.put("is_returned", resultSet.getInt("is_returned"));


                borrowHistories.add(borrowHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
        }

        return borrowHistories;
    }




    public void closeConnection() {
        DatabaseConnection.close(connection);
    }
}
