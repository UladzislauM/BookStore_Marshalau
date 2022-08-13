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
            req.setAttribute("book", book);
        } catch (Exception e) {
            log.error("Exception by BookCommand {}", e);
        }
        return "book.jsp";
    }
}
