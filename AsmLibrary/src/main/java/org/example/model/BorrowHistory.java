package org.example.model;

public class BorrowHistory {
    private int borrow_id;
    private int book_id;
    private int ticket_id;
    private int is_returned;

    public BorrowHistory() {
    }

    public BorrowHistory(int borrow_id, int book_id, int ticket_id, int is_returned) {
        this.borrow_id = borrow_id;
        this.book_id = book_id;
        this.ticket_id = ticket_id;
        this.is_returned = is_returned;
    }

    public int getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(int borrow_id) {
        this.borrow_id = borrow_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getIs_returned() {
        return is_returned;
    }

    public void setIs_returned(int is_returned) {
        this.is_returned = is_returned;
    }
}
