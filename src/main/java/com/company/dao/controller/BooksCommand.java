package com.company.dao.controller;

import com.company.dao.entity.Book;
import com.company.dao.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BooksCommand implements Command {
    private final BookService bookService;

    public BooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    private static final Logger log = LogManager.getLogger(BooksCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BooksCommand {}", req);
        try {
            List<Book> books = bookService.getAllBooks();
            req.setAttribute("books", books);
        } catch (Exception e) {
            log.error("Exception by BooksCommand {}", e);
        }
        return "books.jsp";
    }
}

