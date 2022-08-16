package com.company.dao.controller.BookControllers;

import com.company.dao.controller.Command;
import com.company.dao.entity.Book;
import com.company.dao.service.serviceImpl.BookBookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookCommand implements Command {
    private final BookBookServiceImpl bookServiceImpl;
    private Book book;

    public BookCommand(BookBookServiceImpl bookServiceImpl, Book book) {
        this.bookServiceImpl = bookServiceImpl;
        this.book = book;
    }

    private static final Logger log = LogManager.getLogger(BookCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BookCommand {}", req.getParameter("id"));
        try {
            book = bookServiceImpl.getBookById(Long.parseLong(req.getParameter("id")));
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
