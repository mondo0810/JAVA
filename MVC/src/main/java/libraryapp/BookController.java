package libraryapp;

import java.util.List;

public class BookController {
    private Library library;

    public BookController(Library library) {
        this.library = library;
    }

    public void addBook(Book book) {
        library.addBook(book);
    }

    public List<Book> searchByTitle(String title) {
        return library.searchByTitle(title);
    }

    public List<Book> searchByAuthor(String author) {
        return library.searchByAuthor(author);
    }

    public List<Book> searchBookByStudentId(int id) {
        return library.searchBookByStudentId(id);
    }

    public void borrowBook(Student student, Book book) {
        library.borrowBook(student, book);
    }

    public void returnBook(Student student, Book book) {
        library.returnBook(student, book);
    }

    public List<Book> getAllBooks() {
        return library.getAllBooks();
    }
}
