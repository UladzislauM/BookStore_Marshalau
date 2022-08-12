package com.company.dao.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/indexPull")
public class IndexPagePull extends HttpServlet {
    private static final Logger log = LogManager.getLogger(IndexPagePull.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String commandConsole = req.getParameter("textPull");
            log.debug(commandConsole);
            String[] commandConsoleArr = commandConsole.split(" ");
            commandConsole = commandConsoleArr[0].toLowerCase();
            switch (commandConsole) {
                case "users":
                    new GetAllUsersHttp().doGet(req, resp);
                    break;
                case "books":
                    new GetAllBooksHttp().doGet(req, resp);
                    break;
                default:
                    resp.getWriter().println("<!DOCTYPE html>" +
                            "<html lang=\"en\" dir=\"ltr\">" +
                            "  <head>" +
                            "    <meta charset=\"utf-8\">" +
                            "    <title>BookStore</title>" +
                            "  </head>" +
                            "  <body>" +
                            "    <h4 align=\"center\">This command false. Please try again.</h4>" +
                            "<hr>" +
                            "<h2><a href=\"index\">Back</a></h2>" +
                            "</body></html>");
            }
        } catch (IOException e) {
            log.error("Servlet Pull Exception {}", e);
        }
    }
}
