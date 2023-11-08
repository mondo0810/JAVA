package org.example.service;

import org.example.dao.BookDAO;
import org.example.model.Book;
import org.example.model.BorrowHistory;
import org.example.model.BorrowTicket;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class BookService {
    private BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public List<Map<String, Object>> getAllBorrowHistory() {
        return bookDAO.getAllBorrowHistory();
    }
    public List<Book> searchBooks(String code, String name) {
        return bookDAO.searchBooks(code, name);
    }

    public Book getBookById(int bookId) {
        return bookDAO.getBookById(bookId);
    }

    public boolean borrowBook(int bookId, int studentId, String dueDate) {
        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            return false; // Sách không tồn tại
        }

        if (book.isIs_borrowed()) {
            return false; // Sách đã được mượn
        }

        // Tạo một phiếu mượn sách
        BorrowTicket borrowTicket = new BorrowTicket();
        borrowTicket.setStudent_id(studentId);
        borrowTicket.setDue_date(dueDate);

        // Tạo một bản ghi lịch sử mượn sách
        BorrowHistory borrowHistory = new BorrowHistory();
        borrowHistory.setBook_id(bookId);

        // Gọi DAO để thực hiện việc mượn sách
        boolean success = bookDAO.borrowBook(borrowTicket, borrowHistory);

        if (success) {
            // Cập nhật trạng thái sách đã mượn
            book.setIs_borrowed(true);
            bookDAO.updateBookStatus(book);
        }

        return success;
    }

    public boolean returnBook(int bookId, int studentId) {
        boolean returnSuccess = bookDAO.returnBook(bookId, studentId);

        if (returnSuccess) {
            Book book = bookDAO.getBookById(bookId);
            if (book != null) {
                book.setIs_borrowed(false);
                bookDAO.updateBookStatus(book);
            }
        }

        return returnSuccess;
    }



}
