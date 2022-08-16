package com.company.dao.controller;

import com.company.dao.service.serviceImpl.BookBookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookDelete implements Command {
    private final BookBookServiceImpl bookServiceImpl;

    public BookDelete(BookBookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    private static final Logger log = LogManager.getLogger(BookDelete.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BookDelete {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            boolean checkDelete = bookServiceImpl.deleteBookById(Long.parseLong(req.getParameter("id")));
            if (!checkDelete) {
                log.error("The book does not deleted, BookDelete");
                req.setAttribute("errorMessage", "Ops..... The book does not deleted, BookDelete");
                return "error.jsp";
            } else {
                req.setAttribute("books", bookServiceImpl.getAllBooks());
                return "books.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by BookDelete {}", e);
            req.setAttribute("errorMessage", "Ops..... <p>The book does not deleted: </p>" + e);
            return "error.jsp";
        }
    }
}
