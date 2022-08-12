package com.company.dao.controller;

import com.company.dao.dao.daoImpl.BookDaoImpl;
import com.company.dao.entity.Book;
import com.company.dao.service.BookService;
import com.company.dao.util.DataSourceElephant;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@WebServlet("/allBooks")
public class GetAllBooksHttp extends HttpServlet {
    private static final Logger log = LogManager.getLogger(GetAllBooksHttp.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Start getAllBooksHttp {}", req.getParameter("id"));
        try (DataSourceElephant dataSourceElephant = new DataSourceElephant()) {
            BookService bookService = new BookService(new BookDaoImpl(dataSourceElephant));
            List<Book> books = bookService.getAllBooks();
            resp.getWriter().println("<!DOCTYPE html><html><head><title>BookStore</title></head>");
            resp.getWriter().println("<body>");
            resp.getWriter().println("<h1>AllBooks (abbreviated representation): \n</h1><hr>");
            resp.getWriter().println("<table>" +
                    "      <tr>" +
                    "        <th>Id</th>" +
                    "        <th>Title</th>" +
                    "        <th>Author</th>" +
                    "        <th>DataPurchase</th>" +
                    "      </tr>");
            for (int i = 0; i < books.size(); i++) {
                resp.getWriter().printf("<tr><td>%d   </td><td><a href=\"getbookbyid?id=%d\">%s</a></td>" +
                                "<td>%s</td><td>%td-%<tm-%<tY</td></tr>",
                        i, books.get(i).getId(), books.get(i).getTitle(), books.get(i).getNameAuthor(),
                        books.get(i).getDateReleaseBook());
            }
            resp.getWriter().println("</table></body></html>");
        } catch (Exception e) {
            log.error("Exception by GetAllBooksHttp {}", e);
        }
    }
}

