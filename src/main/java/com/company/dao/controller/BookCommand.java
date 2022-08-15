package com.company.dao.controller;

import com.company.dao.entity.Book;
import com.company.dao.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookCommand implements Command {
    private final BookService bookService;

    public BookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    private static final Logger log = LogManager.getLogger(BookCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BookCommand {}", req.getParameter("id"));
        try {
            Book book = bookService.getBookById(Long.parseLong(req.getParameter("id")));
            if (book.getId() == 0) {
                log.error("The book does not exist, BookCommand");
                req.setAttribute("errorMessage", "The book does not exist, BookCommand");
                return "error.jsp";
            } else {
                req.setAttribute("book", book);
                return "book.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by BookCommand {}", e);
            req.setAttribute("errorMessage", "The book does not exist, BookCommand: " + e);
            return "error.jsp";
        }
    }
}
