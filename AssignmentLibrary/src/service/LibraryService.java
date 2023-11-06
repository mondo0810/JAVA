package service;

public class LibraryService {
    private BookDAO bookDAO;
    private StudentDAO studentDAO;
    private BorrowHistoryDAO borrowHistoryDAO;
    private BorrowTicketDAO borrowTicketDAO;

    public LibraryService(Connection connection) {
        bookDAO = new BookDAO(connection);
        studentDAO = new StudentDAO(connection);
        borrowHistoryDAO = new BorrowHistoryDAO(connection);
        borrowTicketDAO = new BorrowTicketDAO(connection);
    }

    public void viewAllBooks() {
        bookDAO.getAllBooks();
    }

    public void viewAllStudents() {
        studentDAO.getAllStudents();
    }

    // Các phương thức khác cho xử lý logic kinh doanh
}
