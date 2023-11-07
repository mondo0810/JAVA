package org.example.model;

public class BorrowTicket {
    private int ticket_id;
    private int student_id;
    private String borrow_date;
    private String due_date;

    public BorrowTicket(int ticket_id, int student_id, String borrow_date, String due_date) {
        this.ticket_id = ticket_id;
        this.student_id = student_id;
        this.borrow_date = borrow_date;
        this.due_date = due_date;
    }

    public BorrowTicket() {

    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
}
