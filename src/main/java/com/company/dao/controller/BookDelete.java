package com.company.dao.controller;

import com.company.dao.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookDelete implements Command {
    private final BookService bookService;

    public BookDelete(BookService bookService) {
        this.bookService = bookService;
    }

    private static final Logger log = LogManager.getLogger(BookDelete.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BookDelete {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            boolean checkDelete = bookService.deleteBookById(Long.parseLong(req.getParameter("id")));
            if (!checkDelete) {
                req.setAttribute("error", "The book does not deleted");
                log.error("The book does not deleted");
                return "error.jsp";
            } else {
                req.setAttribute("books", bookService.getAllBooks());
                return "books.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by BookDelete {}", e);
            req.setAttribute("error", "The book does not deleted: " + e);
            log.error("The book does not deleted");
            return "error.jsp";
        }
    }
}