package com.company.dao.service;

import com.company.dao.entity.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Object getBookById(Long id);

    boolean deleteBookById(Long id);

    Book createBook(Book book);

    Book updateBookById(Book book);

    Book getBookByISBN(String isbn);

    List<Book> getBookByAuthor(String author);

    Long countAllBooks();

    BigDecimal sumBooksByAuthor(String author);
}
