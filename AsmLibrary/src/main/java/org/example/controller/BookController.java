package org.example.controller;


import org.example.model.Book;
import org.example.model.BorrowHistory;
import org.example.service.BookService;
import org.example.view.BookView;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class BookController {
    private BookService bookService;
    private BookView bookView;

    public BookController(BookService bookService, BookView bookView) {
        this.bookService = bookService;
        this.bookView = bookView;
    }

    public void getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        bookView.displayBooks(books);
    }

    public void getAllBorrowHistory() {
        List<Map<String, Object>> historys = bookService.getAllBorrowHistory();
        bookView.displayAllBorrowHistory(historys);
    }

    public void searchBooks(String code, String name) {
        List<Book> books = bookService.searchBooks(code, name);
        bookView.displayBooks(books);
    }

    public void viewBookDetails(int bookId) {
        Book book = bookService.getBookById(bookId);
        bookView.displayBookDetails(book);
    }

    public void borrowBook(int bookId, int studentId,  String dueDate) {
        boolean success = bookService.borrowBook(bookId, studentId, dueDate);
        if (success) {
            bookView.displayMessage("Book has been borrowed successfully.");
            getAllBooks();
        } else {
            bookView.displayMessage("Books have been borrowed by students");
        }
    }

    public void returnBook(int bookId, int studentId) {
        boolean success = bookService.returnBook(bookId, studentId);
        if (success) {
            bookView.displayMessage("Book has been returned successfully.");
            getAllBooks();
        } else {
            bookView.displayMessage("Failed to return the book.");
        }
    }


}
