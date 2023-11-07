package view;

import model.Book;

import java.util.List;

public class BookView {
    public void displayBooks(List<Book> books) {
        System.out.println("List of Books:");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-30s %-20s %-10s\n", "ID", "Code", "Name", "Author", "Status");
        System.out.println("--------------------------------------------------------------");

        for (Book book : books) {
            System.out.printf("%-5d %-15s %-30s %-20s %s\n", book.getBook_id(), book.getCode(), book.getName(), book.getAuthor(), (book.getIsBorrowed() ? "Borrowed" : "Available"));
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
        System.out.println("Status: " + (book.getIsBorrowed() ? "Borrowed" : "Available"));
        System.out.println("--------------------------------------------------------------");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
