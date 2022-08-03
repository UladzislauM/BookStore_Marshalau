package com.company.dao.service;

import com.company.dao.entity.Book;
import com.company.dao.module.BookDao;

import java.util.List;

public class BookService {
    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> getAllBooks() {
        System.out.println("Start method Service.GetAllBooks");

        return bookDao.getAll();
    }

    public Book getBookById(Long id) {
        System.out.println("Start method Service.getBooksByID");
        return bookDao.getById(id);
    }

    public void deleteBookById(Long id) {
        System.out.println("Start method Service.deleteBooksByID");
        if (bookDao.delete(id)) {
            System.out.println("Delete true");
        } else {
            System.out.println("Delete false");
        }
    }

    public Book createBook(Book book) {
        System.out.println("Start method Service.addBooksByID");
        return bookDao.create(book);
    }

    public Book updateBookById(Book book) {
        System.out.println("Start method Service.updateBookById");

        return bookDao.update(book);
    }

    public void getBookByISBN(String isbn) {
        System.out.println("Start method Service.getBookByISBN");
        Book book = bookDao.getBookByISBN(isbn);
        System.out.println("Book by isbn " + isbn + " :" + book);
    }

    public void getBookByAuthor(String author) {
        System.out.println("Start method Service.getBookByAuthor");
        List<Book> books = bookDao.getBooksByAuthor(author);
        if (books != null) {
            books.forEach(System.out::println);
        } else {
            System.out.println("Not Found");
        }
    }

    public void countAllBooks() {
        System.out.println("Start method Service.countAllBooks");
        System.out.println("Count All books: ");
        System.out.println(bookDao.countAllBooks());
    }
}
