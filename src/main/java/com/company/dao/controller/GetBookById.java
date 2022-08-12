package com.company.dao.controller;

import com.company.dao.dao.daoImpl.BookDaoImpl;
import com.company.dao.service.BookService;
import com.company.dao.util.DataSourceElephant;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/getbookbyid")
public class GetBookById extends HttpServlet {
    private static final Logger log = LogManager.getLogger(GetBookById.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Start GetBookById {}", req.getParameter("id"));
        try (DataSourceElephant dataSourceElephant = new DataSourceElephant()) {
            BookService bookService = new BookService(new BookDaoImpl(dataSourceElephant));
            resp.getWriter().println("<!DOCTYPE html><html><head><title>BookStore</title></head>");
            resp.getWriter().println("<body>");
            resp.getWriter().printf("<h1>%s</h1><hr>",
                    bookService.getBookById(Long.parseLong(req.getParameter("id"))).getNameAuthor());
            resp.getWriter().printf("<p>* Title: %s</p>" +
                            "<p>* Date release book: %td-%<tm-%<tY</p>" +
                            "<p>* Price: %.2f</p>" +
                            "<p>* ISBN: %s</p>" +
                            "<p>* Status: %s</p>",
                    bookService.getBookById(Long.parseLong(req.getParameter("id"))).getTitle(),
                    bookService.getBookById(Long.parseLong(req.getParameter("id"))).getDateReleaseBook(),
                    bookService.getBookById(Long.parseLong(req.getParameter("id"))).getPrice(),
                    bookService.getBookById(Long.parseLong(req.getParameter("id"))).getIsbn(),
                    bookService.getBookById(Long.parseLong(req.getParameter("id"))).getStatus());

            resp.getWriter().println("</body></html>");
        } catch (Exception e) {
            log.error("Exception by GetBookById {}", e);
        }
    }
}
