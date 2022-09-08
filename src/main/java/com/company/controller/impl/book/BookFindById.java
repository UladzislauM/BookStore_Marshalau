package com.company.controller.impl.book;

import com.company.controller.Command;
import com.company.entity.Book;
import com.company.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookFindById implements Command {
    private static final Logger log = LogManager.getLogger(BookFindById.class);
    private final BookService bookService;

    public BookFindById(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start BookCommand {}", req.getParameter("id"));
        Book bookDto = bookService.findById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("book", bookDto);
        return "JSP/book.jsp";
    }
}
