package com.company.dao.module;

import com.company.dao.base.Book;

import java.util.List;

public interface BookDao {
    Book create(Book book);

    Book getById (Long id);
    Book getBookByISBN (String isbn);
    List<Book> getBooksByAuthor(String author);
    long countAllBooks();
    List<Book> getAll();

    Book update(Book book);

    boolean delete(Long id);
}
