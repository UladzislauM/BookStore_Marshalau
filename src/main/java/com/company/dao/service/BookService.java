package com.company.dao.service;

import com.company.dao.entity.Book;
import com.company.dao.entity.User;
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
        List<Book> books = bookDao.getAll();
        if(books == null) {
            throw new RuntimeException("There are no books in the table");
        }
        return books;
    }

    public Book getBookById(Long id) {
        System.out.println("Start method Service.getBooksByID");
        Book book = bookDao.getById(id);
        if(book == null){
            throw new RuntimeException("Book not found.");
        }
        return book;
    }

    public void deleteBookById(Long id) {
        System.out.println("Start method Service.deleteBooksByID");
        if(!bookDao.delete(id)) {
            throw new RuntimeException("Such a user cannot be deleted");
        }
    }

    public Book createBook(Book book) {
        System.out.println("Start method Service.addBooksByID");
        book = bookDao.create(book);
        if(book == null) {
            throw new RuntimeException("Such a book cannot be added");
        }
        return book;
    }

    public Book updateBookById(Book book) {
        System.out.println("Start method Service.updateBookById");
        book = bookDao.update(book);
        if(book == null) {
            throw new RuntimeException("Such a book cannot be updated");
        }
        return book;
    }

    public Book getBookByISBN(String isbn) {
        System.out.println("Start method Service.getBookByISBN");
        Book book = bookDao.getBookByISBN(isbn);
        if(book == null) {
            throw new RuntimeException("Book not found.");
        }
        return book;
    }

    public List<Book> getBookByAuthor(String author) {
        System.out.println("Start method Service.getBookByAuthor");
        List<Book> books = bookDao.getBooksByAuthor(author);
        if(books == null) {
            throw new RuntimeException("Book not found.");
        }
        return books;
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
        throw new RuntimeException("Books not found");
    }

}
