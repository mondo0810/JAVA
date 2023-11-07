package org.example.service;

import org.example.dao.BookDAO;
import org.example.model.Book;
import org.example.model.BorrowHistory;
import org.example.model.BorrowTicket;

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

    public boolean borrowBook(int bookId, int studentId, String borrowDate, String dueDate) {
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
        borrowTicket.setBorrow_date(borrowDate);
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

    public boolean returnBook(int bookId, int studentId, String returnDate) {
        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            return false; // Sách không tồn tại
        }

        if (!book.isIs_borrowed()) {
            return false; // Sách chưa được mượn
        }

        // Tìm thông tin phiếu mượn sách của học viên
        BorrowTicket borrowTicket = bookDAO.findBorrowTicket(bookId, studentId);

        if (borrowTicket == null) {
            return false; // Không tìm thấy thông tin phiếu mượn
        }

        // Cập nhật thông tin trả sách trong phiếu mượn
        borrowTicket.setDue_date(returnDate);

        // Cập nhật trạng thái sách đã được trả
        book.setIs_borrowed(false);

        // Gọi DAO để thực hiện việc trả sách111
        boolean success = bookDAO.returnBook(borrowTicket);

        if (success) {
            // Cập nhật trạng thái sách đã trả
            bookDAO.updateBookStatus(book);
        }

        return success;
    }



}
