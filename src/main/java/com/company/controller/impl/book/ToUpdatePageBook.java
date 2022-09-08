package com.company.controller.impl.book;

import com.company.controller.Command;
import com.company.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToUpdatePageBook implements Command {
    private static final Logger log = LogManager.getLogger(ToUpdatePageBook.class);
    private final BookService bookService;

    public ToUpdatePageBook(BookService bookService) {
        this.bookService = bookService;
    }

    public String execute(HttpServletRequest req) {
        log.info("Start ToUpdatePageBook {}", req.getParameter("id"));
        req.setAttribute("book", bookService.findById(Long.parseLong(req.getParameter("id"))));
        return "JSP/updateBook.jsp";
    }
}
