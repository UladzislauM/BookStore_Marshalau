package com.company.service.serviceImpl;

import com.company.dao.BookDao;
import com.company.entity.Book;
import com.company.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class BookServiceImpl implements BookService {
    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);
    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = bookDao.findAll();
        log.debug("Start BookService - getAllBooks - {}", books.size());
        return books;
    }

    @Override
    public Book findById(Long id) {
        log.debug("Start BookService - getBookById {}", id);
        return bookDao.findById(id);
    }

    @Override
    public void delete(Long id) {
        if (bookDao.delete(id)) {
            log.debug("Start BookService - deleteBookById: {}", id);
        } else {
            log.error("BookService - deleteBookById false: {}", id);
            throw new RuntimeException("DeleteBook - Book is not exist...");
        }
    }

    @Override
    public Book create(Book book) {
        log.debug("Start BookService - createBook {}", book);
        return bookDao.create(book);
    }

    @Override
    public Book update(Book book) {
        log.debug("Start BookService - updateBookById {}", book);
        return bookDao.update(book);
    }

    @Override
    public Book getBookByISBN(String isbn) {
        log.debug("Start BookService - getBookByISBN {}", isbn);
        return bookDao.findBookByISBN(isbn);
    }

    @Override
    public List<Book> getBookByAuthor(String author) {
        log.debug("Start BookService - getBookByISBN {}", author);
        List<Book> books = bookDao.findBooksByAuthor(author);
        return books;
    }

    @Override
    public Long countAll() {
        log.debug("Start BookService - countAllBooks");
        return bookDao.countAll();
    }

    @Override
    public BigDecimal sumBooksByAuthor(String author) {
        log.debug("Start BookService - sumBooksByAuthor {}", author);
        List<Book> books = bookDao.findBooksByAuthor(author);
        try {
            if (books.size() != 0) {
                BigDecimal sum = books.get(0).getPrice();
                for (int i = 1; i < books.size(); i++) {
                    sum = sum.add(books.get(i).getPrice());
                }
                return sum;
            }
        } catch (Exception e) {
            log.error("Method error - sumBooksByAuthor: {}", e);
        }
        throw new RuntimeException("Method error - sumBooksByAuthor");
    }
}
