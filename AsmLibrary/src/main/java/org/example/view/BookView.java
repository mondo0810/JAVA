package org.example.view;


import org.example.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookView {
    public void displayBooks(List<Book> books) {
        System.out.println("List of Books:");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-30s %-20s %-10s\n", "ID", "Code", "Name", "Author", "Status");
        System.out.println("--------------------------------------------------------------");

        for (Book book : books) {
            System.out.printf("%-5d %-15s %-30s %-20s %s\n", book.getBook_id(), book.getCode(), book.getName(), book.getAuthor(), (book.isIs_borrowed() ? "Borrowed" : "Available"));
        }
        System.out.println("--------------------------------------------------------------");
    }

    public void displayBookDetails(Book book) {
        System.out.println("Book Details:");
        System.out.println("--------------------------------------------------------------");
        System.out.println("ID: " + book.getBook_id());
        System.out.println("Code: " + book.getCode());
        System.out.println("Name: " + book.getName());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Status: " + (book.isIs_borrowed() ? "Borrowed" : "Available"));
        System.out.println("--------------------------------------------------------------");
    }

    public void displayAllBorrowHistory(List<ResultSet> historyList) {
        for (ResultSet resultSet : historyList) {
            try {
                while (resultSet.next()) {
                    int borrow_id = resultSet.getInt("borrow_id");
                    int book_id = resultSet.getInt("book_id");
                    int ticket_id = resultSet.getInt("ticket_id");
                    boolean is_returned = resultSet.getBoolean("is_returned");

                    // Hiển thị thông tin lịch sử mượn sách
                    System.out.println("Borrow ID: " + borrow_id);
                    System.out.println("Book ID: " + book_id);
                    System.out.println("Ticket ID: " + ticket_id);
                    System.out.println("Is Returned: " + is_returned);
                    System.out.println("------------------------------");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
