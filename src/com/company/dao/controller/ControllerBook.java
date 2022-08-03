package com.company.dao.controller;

import com.company.dao.base.Book;
import com.company.dao.module.BookDao;

import java.util.List;

public class ControllerBook {
    private final BookDao bookDao;

    public ControllerBook(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void getAllBooks() {
        System.out.println("Start method Service.GetAllBooks");
        List<Book> books = bookDao.getAll();
        System.out.println("AllBooks (abbreviated representation): ");
        for (int i = 0; i < books.size(); i++) {
            System.out.printf("ID: %d, BookName: %s, NameAuthor: %s, DataPurchase: %td-%<tm-%<tyy %n",
                    books.get(i).getId(), books.get(i).getTitle(), books.get(i).getNameAuthor(),
                    books.get(i).getDateReleaseBook());
        }
    }

    public void getBookById(Long id) {
        System.out.println("Start method Service.getBooksByID");
        System.out.println("Book by number " + id + " :" + bookDao.getById(id));
    }

    public void deleteBookById(Long id) {
        System.out.println("Start method Service.deleteBooksByID");
        if (bookDao.delete(id)) {
            System.out.println("Delete true");
        } else {
            System.out.println("Delete false");
        }
    }

    public void addBookById(Book book) {
        System.out.println("Start method Service.addBooksByID");
        System.out.println(bookDao.create(book));
    }

    public void updateBookById(Book book) {
        System.out.println("Start method Service.updateBookById");
        bookDao.update(book);
        System.out.println(book);
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
