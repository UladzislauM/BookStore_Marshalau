package com.company.dao.service;

import com.company.dao.entity.Book;
import com.company.dao.module.BookDao;

import java.math.BigDecimal;
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

    public boolean deleteBookById(Long id) {
        System.out.println("Start method Service.deleteBooksByID");
        return bookDao.delete(id);
    }

    public Book createBook(Book book) {
        System.out.println("Start method Service.addBooksByID");
        return bookDao.create(book);
    }

    public Book updateBookById(Book book) {
        System.out.println("Start method Service.updateBookById");
        return bookDao.update(book);
    }

    public Book getBookByISBN(String isbn) {
        System.out.println("Start method Service.getBookByISBN");
        return bookDao.getBookByISBN(isbn);
    }

    public List<Book> getBookByAuthor(String author) {
        System.out.println("Start method Service.getBookByAuthor");
        return bookDao.getBooksByAuthor(author);
    }

    public Long countAllBooks() {
        System.out.println("Start method Service.countAllBooks");
        return bookDao.countAllBooks();
    }

    public BigDecimal sumBooksByAuthor(String author) {
        System.out.println("Start method Service.sumBooksByAuthor");
        List<Book> books = bookDao.getBooksByAuthor(author);
        if (books.size() != 0) {
            BigDecimal sum = books.get(0).getPrice();
            for (int i = 1; i < books.size(); i++) {
                sum = sum.add(books.get(i).getPrice());
            }
            return sum;
        }
        return null;
    }

}
