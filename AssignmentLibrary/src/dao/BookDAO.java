package dao;

import config.DatabaseConnection;
import model.Book;

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
                int book_id = resultSet.getInt("id");
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
        String query = "SELECT * FROM books WHERE code = ? OR name = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, name);
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
                int id = resultSet.getInt("book_id");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                boolean isBorrowed = resultSet.getBoolean("is_borrowed");

                book = new Book(id, code, name, author, isBorrowed);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public boolean updateBookStatus(int bookId, boolean isBorrowed) {
        String query = "UPDATE books SET is_borrowed = ? WHERE book_id = ?";
        boolean success = false;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, isBorrowed);
            preparedStatement.setInt(2, bookId);

            int rowsUpdated = preparedStatement.executeUpdate();
            success = rowsUpdated > 0;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public void closeConnection() {
        DatabaseConnection.close(connection);
    }
}
