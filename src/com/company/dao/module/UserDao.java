package com.company.dao.module;

import com.company.dao.base.Book;

import java.util.List;

public interface UserDao {
    Book create(Book book);

    Book getById (Long Id);
    List<Book> getAll();

    Book update(Book book);

    boolean delete(Long id);
}
