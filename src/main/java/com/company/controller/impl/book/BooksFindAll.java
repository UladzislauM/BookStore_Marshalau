package com.company.controller.impl.book;

import com.company.controller.Command;
import com.company.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BooksFindAll implements Command {
    private static final Logger log = LogManager.getLogger(BooksFindAll.class);
    private final BookService bookService;

    public BooksFindAll(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start BooksCommand {}", req);
        req.setAttribute("book_count", bookService.countAll());
        req.setAttribute("books", bookService.findAll());
        return "JSP/books.jsp";
    }
}

