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

public class BookCreate implements Command {
    private static final Logger log = LogManager.getLogger(BookCreate.class);
    private final BookService bookService;

    public BookCreate(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start BookCreate {}", req.getParameter("id"));
        Book bookDto = addBookKeyBoard(req);
        bookService.create(bookDto);
        req.setAttribute("book_count", bookService.countAll());
        req.setAttribute("books", bookService.findAll());
        return "JSP/books.jsp";
    }


    private Book addBookKeyBoard(HttpServletRequest req) {
        Book book = new Book();
        book.setTitle(req.getParameter("title"));
        book.setNameAuthor(req.getParameter("name_author"));
        String dataNull = req.getParameter("data_purchase");
        List<String> dataArr = Arrays.asList(dataNull.split("-"));
        book.setDateReleaseBook(LocalDate.of
                (Integer.parseInt(dataArr.get(0)), Integer.parseInt(dataArr.get(1)), Integer.parseInt(dataArr.get(2))));
        book.setStatus(StatusBook.valueOf(req.getParameter("status_book")));
        book.setPrice(BigDecimal.valueOf(Integer.parseInt(req.getParameter("price"))));
        book.setIsbn(req.getParameter("isbn"));
        return book;
    }
}
