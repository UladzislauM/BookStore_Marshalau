package com.company.dao.controller;

import com.company.dao.entity.Book;
import com.company.dao.entity.StatusBook;
import com.company.dao.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class BookCreate implements Command{
    private final BookService bookService;

    public BookCreate(BookService bookService) {
        this.bookService = bookService;
    }

    private static final Logger log = LogManager.getLogger(BookCreate.class);
    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BookCreate {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            Book book = addBookKeyBoard(req);
            if (book.getTitle() == null) {
                req.setAttribute("error", "The book does not deleted");
                log.error("The book does not deleted");
                return "error.jsp";
            } else {
                bookService.createBook(book);
                req.setAttribute("books", bookService.getAllBooks());
                return "books.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by BookCreate {}", e);
            req.setAttribute("error", "The book does not deleted");
            log.error("The book does not deleted");
            return "error.jsp";
        }
    }
    private Book addBookKeyBoard(HttpServletRequest req) {
        Book book = new Book();
        book.setTitle(req.getParameter("title"));
        book.setNameAuthor(req.getParameter("name_author"));
        String dataNull =  req.getParameter("data_purchase");
        List<String> dataArr = Arrays.asList(dataNull.split("-"));
        book.setDateReleaseBook(LocalDate.of
                (Integer.parseInt(dataArr.get(0)), Integer.parseInt(dataArr.get(1)), Integer.parseInt(dataArr.get(2))));
        String statusStr = req.getParameter("status_book");
        book.setStatus(StatusBook.valueOf(statusStr));
        book.setPrice(BigDecimal.valueOf(Integer.parseInt(req.getParameter("price"))));
        book.setIsbn(req.getParameter("isbn"));
        return book;
    }
}