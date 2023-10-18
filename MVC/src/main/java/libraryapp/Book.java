package libraryapp;

import java.util.Date;

public class Book {
    private String title;
    private String author;
    private double price;
    private Date dateAdded;
    private String status;

    public Book(String title, String author, double price, Date dateAdded, String status) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.dateAdded = dateAdded;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
