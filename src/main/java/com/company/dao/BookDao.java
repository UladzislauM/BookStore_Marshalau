package com.company.dao;

import com.company.entity.Book;

import java.util.List;

public interface BookDao extends AbstractDao<Book>{
    Book findBookByISBN(String isbn);

    List<Book> findBooksByAuthor(String author);
}
