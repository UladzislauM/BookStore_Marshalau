package com.company.dao.service;

import com.company.dao.entity.Book;
import com.company.dao.entity.User;
import com.company.dao.module.BookDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class BookService {
    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> getAllBooks() {
        logger.log(Level.DEBUG, "Start BookService - getAllBooks");
        List<Book> books = bookDao.getAll();
        if (books == null) {
            logger.log(Level.ERROR, "There are no books in the table");
            throw new RuntimeException("There are no books in the table");
        }
        return books;
    }

    public Book getBookById(Long id) {
        logger.log(Level.DEBUG, "Start BookService - getBookById");
        Book book = bookDao.getById(id);
        if (book == null) {
            logger.log(Level.ERROR, "Book not found.");
            throw new RuntimeException("Book not found.");
        }
        return book;
    }

    public void deleteBookById(Long id) {
        logger.log(Level.DEBUG, "Start BookService - deleteBookById");
        if (!bookDao.delete(id)) {
            logger.log(Level.ERROR, "Such a user cannot be deleted");
            throw new RuntimeException("Such a user cannot be deleted");
        }
    }

    public Book createBook(Book book) {
        logger.log(Level.DEBUG, "Start BookService - createBook");
        book = bookDao.create(book);
        if (book == null) {
            logger.log(Level.ERROR, "Such a user cannot be added");
            throw new RuntimeException("Such a book cannot be added");
        }
        return book;
    }

    public Book updateBookById(Book book) {
        logger.log(Level.DEBUG, "Start BookService - updateBookById");
        book = bookDao.update(book);
        if (book == null) {
            logger.log(Level.ERROR, "Such a user cannot be updated");
            throw new RuntimeException("Such a book cannot be updated");
        }
        return book;
    }

    public Book getBookByISBN(String isbn) {
        logger.log(Level.DEBUG, "Start BookService - getBookByISBN");
        Book book = bookDao.getBookByISBN(isbn);
        if (book == null) {
            logger.log(Level.ERROR, "Book not found.");
            throw new RuntimeException("Book not found.");
        }
        return book;
    }

    public List<Book> getBookByAuthor(String author) {
        logger.log(Level.DEBUG, "Start BookService - getBookByAuthor");
        List<Book> books = bookDao.getBooksByAuthor(author);
        if (books == null) {
            logger.log(Level.ERROR, "Book not found.");
            throw new RuntimeException("Book not found.");
        }
        return books;
    }

    public Long countAllBooks() {
        logger.log(Level.DEBUG, "Start BookService - countAllBooks");
        return bookDao.countAllBooks();
    }

    public BigDecimal sumBooksByAuthor(String author) {
        logger.log(Level.DEBUG, "Start BookService - sumBooksByAuthor");
        List<Book> books = bookDao.getBooksByAuthor(author);
        if (books.size() != 0) {
            BigDecimal sum = books.get(0).getPrice();
            for (int i = 1; i < books.size(); i++) {
                sum = sum.add(books.get(i).getPrice());
            }
            return sum;
        }
        logger.log(Level.ERROR, "Book not found.");
        throw new RuntimeException("Books not found");
    }

    static Logger logger = LogManager.getLogger();
}
