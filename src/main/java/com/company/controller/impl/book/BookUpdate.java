package com.company.controller.impl.book;

import com.company.controller.Command;
import com.company.entity.Book;
import com.company.entity.StatusBook;
import com.company.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class BookUpdate implements Command {
    private static final Logger log = LogManager.getLogger(BookDelete.class);
    private final BookService bookService;

    public BookUpdate(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start BookUpdate {}", req.getParameter("id"));
        Book book = bookService.findById(Long.parseLong(req.getParameter("id")));
        addBookKeyBoard(req, book);
        bookService.update(book);
        req.setAttribute("book_count", bookService.countAll());
        req.setAttribute("book", book);
        return "JSP/book.jsp";
    }

    private void addBookKeyBoard(HttpServletRequest req, Book book) {
        if (req.getParameter("title") != null) {
            book.setTitle(req.getParameter("title"));
        }
        if (req.getParameter("name_author") != null) {
            book.setNameAuthor(req.getParameter("name_author"));
        }
        if (req.getParameter("data_purchase") != null) {
            String dataNull = req.getParameter("data_purchase");
            List<String> dataArr = Arrays.asList(dataNull.split("-"));
            book.setDateReleaseBook(LocalDate.of
                    (Integer.parseInt(dataArr.get(0)), Integer.parseInt(dataArr.get(1)), Integer.parseInt(dataArr.get(2))));
        }
        if (req.getParameter("status_book") != null) {
            String statusStr = req.getParameter("status_book");
            book.setStatus(StatusBook.valueOf(statusStr));
        }
        if (req.getParameter("price") != null) {
            book.setPrice(new BigDecimal(req.getParameter("price")));
        }
        if (req.getParameter("isbn") != null) {
            book.setIsbn(req.getParameter("isbn"));
        }
    }
}
