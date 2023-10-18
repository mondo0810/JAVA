package libraryapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    private Connection connection;

    public Library() throws SQLException {
        this.books = new ArrayList<>();
        this.connection = DBConnection.getConnection();
    }

    public void addBook(Book book) {
        String query = "INSERT INTO books (title, author, price, date_added, status) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setDate(4, new java.sql.Date(book.getDateAdded().getTime()));
            preparedStatement.setString(5, book.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public void borrowBook(Student student, Book book) {
        student.borrowBook(book);
    }


    public void returnBook(Student student, Book book) {
        student.getBorrowedBooks().remove(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Book> searchBookByStudentId(int id) {
        return books;
    }
}
