package com.company.service;

import com.company.entity.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService extends AbstractService<Book>{
    Book getBookByISBN(String isbn);

    List<Book> getBookByAuthor(String author);

    BigDecimal sumBooksByAuthor(String author);
}
