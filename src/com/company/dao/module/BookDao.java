package com.company.dao.module;

import com.company.dao.base.Book;

import java.util.List;

public interface BookDao {
    Book create(Book book);

    Book getById (Long id);
    Book getBookByISBN (Integer isbn);
    List<Book> getBooksByAuthor(String author);
    int countAllBooks();
    List<Book> getAll();

    Book update(Book book);

    boolean delete(Long id);
}
