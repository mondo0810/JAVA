package org.example.model;

public class Book {
    private int book_id;
    private String code;
    private String name;
    private String author;
    private boolean is_borrowed;

    public Book(int book_id, String code, String name, String author, boolean is_borrowed) {
        this.book_id = book_id;
        this.code = code;
        this.name = name;
        this.author = author;
        this.is_borrowed = is_borrowed;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isIs_borrowed() {
        return is_borrowed;
    }

    public void setIs_borrowed(boolean is_borrowed) {
        this.is_borrowed = is_borrowed;
    }
}
