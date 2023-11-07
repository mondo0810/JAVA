package controller;

import model.Book;
import service.BookService;
import view.BookView;

import java.util.List;

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

    public void searchBooks(String code, String name) {
        List<Book> books = bookService.searchBooks(code, name);
        bookView.displayBooks(books);
    }

    public void viewBookDetails(int bookId) {
        Book book = bookService.getBookById(bookId);
        bookView.displayBookDetails(book);
    }

    public void borrowBook(int bookId) {
        boolean success = bookService.borrowBook(bookId);
        if (success) {
            bookView.displayMessage("Book has been borrowed.");
        } else {
            bookView.displayMessage("Book is not available for borrowing.");
        }
    }

    public void returnBook(int bookId) {
        boolean success = bookService.returnBook(bookId);
        if (success) {
            bookView.displayMessage("Book has been returned.");
        } else {
            bookView.displayMessage("Book is not available for return.");
        }
    }
}
