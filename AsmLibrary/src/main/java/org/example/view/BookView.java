package org.example.view;


import org.example.model.Book;
import org.example.model.BorrowHistory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    public void displayAllBorrowHistory(List<Map<String, Object>> borrowHistories) {
        System.out.println("Borrow History:");
        for (Map<String, Object> history : borrowHistories) {
            System.out.println("Ticket ID: " + history.get("ticket_id"));
            System.out.println("Student Name: " + history.get("student_name"));
            System.out.println("Book Code: " + history.get("code"));
            System.out.println("Book Name: " + history.get("name"));
            System.out.println("Book Author: " + history.get("author"));
            System.out.println("Borrow Date: " + history.get("borrow_date"));
            System.out.println("Due Date: " + history.get("due_date"));
            System.out.println("Is Returned: " + (history.get("is_returned").equals(1) ? "Book returned" : "Books have not been returned"));
            System.out.println("--------------------------------------");
        }
    }


    public void displayMessage(String message) {
        System.out.println(message);
    }
}
