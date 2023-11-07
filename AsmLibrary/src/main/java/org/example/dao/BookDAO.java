package org.example.dao;

import org.example.config.DatabaseConnection;
import org.example.model.Book;
import org.example.model.BorrowHistory;
import org.example.model.BorrowTicket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
        String ticketInsertQuery = "INSERT INTO borrowtickets (student_id, borrow_date, due_date) VALUES (?, ?, ?)";
        try (PreparedStatement ticketStatement = connection.prepareStatement(ticketInsertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ticketStatement.setInt(1, borrowTicket.getStudent_id());
            ticketStatement.setString(2, borrowTicket.getBorrow_date());
            ticketStatement.setString(3, borrowTicket.getDue_date());

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


    public boolean returnBook(BorrowTicket borrowTicket, BorrowHistory borrowHistory) {
        Connection connection = DatabaseConnection.connect();

        // Thực hiện cập nhật bảng borrowhistory
        String historyInsertQuery = "UPDATE borrowhistory SET is_returned = true WHERE ticket_id = ?";
        try (PreparedStatement historyStatement = connection.prepareStatement(historyInsertQuery)) {
            historyStatement.setInt(1, borrowTicket.getTicket_id());

            int rowsUpdated = historyStatement.executeUpdate();

            if (rowsUpdated <= 0) {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        // Thực hiện cập nhật trạng thái sách đã được trả
        String updateQuery = "UPDATE books SET is_borrowed = false WHERE book_id = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, borrowHistory.getBook_id());

            int rowsUpdated = updateStatement.executeUpdate();

            if (rowsUpdated <= 0) {
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

    public void closeConnection() {
        DatabaseConnection.close(connection);
    }
}
