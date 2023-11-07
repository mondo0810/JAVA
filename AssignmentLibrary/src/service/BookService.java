package service;

import dao.BookDAO;
import model.Book;

import java.util.List;

public class BookService {
    private BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public List<Book> searchBooks(String code, String name) {
        return bookDAO.searchBooks(code, name);
    }

    public Book getBookById(int bookId) {
        return bookDAO.getBookById(bookId);
    }

    public boolean borrowBook(int bookId) {
        return bookDAO.updateBookStatus(bookId, true);
    }

    public boolean returnBook(int bookId) {
        return bookDAO.updateBookStatus(bookId, false);
    }
}
