package com.company.dao.controller;

import com.company.dao.dao.daoImpl.BookDaoImpl;
import com.company.dao.entity.Book;
import com.company.dao.service.BookService;
import com.company.dao.util.DataSourceElephant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

@WebServlet("/allBooks")
public class getAllBooksHttp extends HttpServlet {
    private static final Logger log = LogManager.getLogger(getAllBooksHttp.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        log.info("Start getAllBooksHttp {}", id);
        try (DataSourceElephant dataSourceElephant = new DataSourceElephant()) {
            BookService bookService = new BookService(new BookDaoImpl(dataSourceElephant));
            List<Book> books = bookService.getAllBooks();
            resp.getWriter().println("AllBooks (abbreviated representation): \n");
            for (int i = 0; i < books.size(); i++) {
                resp.getWriter().printf("ID: %d, BookName: %s, NameAuthor: %s, DataPurchase: %td-%<tm-%<tyy %n",
                        books.get(i).getId(), books.get(i).getTitle(), books.get(i).getNameAuthor(),
                        books.get(i).getDateReleaseBook());
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }
}

