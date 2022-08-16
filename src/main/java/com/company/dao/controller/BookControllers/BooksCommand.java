package com.company.dao.controller.BookControllers;

import com.company.dao.controller.Command;
import com.company.dao.entity.Book;
import com.company.dao.service.serviceImpl.BookBookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BooksCommand implements Command {
    private final BookBookServiceImpl bookServiceImpl;

    public BooksCommand(BookBookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    private static final Logger log = LogManager.getLogger(BooksCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BooksCommand {}", req);
        try {
            List<Book> books = bookServiceImpl.getAllBooks();
            req.setAttribute("books", books);
            return "books.jsp";
        } catch (Exception e) {
            log.error("Exception by BooksCommand {}", e);
            req.setAttribute("errorMessage", "Ops..... The book does not exist: " + e);
            return "error.jsp";
        }
    }
}

