package com.company.dao.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/indexPull")
public class indexPagePull extends HttpServlet {
    private static final Logger log = LogManager.getLogger(indexPagePull.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String commandConsole = req.getParameter("textPull");
            resp.getWriter().println(commandConsole);
            log.debug(commandConsole);
            String[] commandConsoleArr = commandConsole.split(" ");
            commandConsole = commandConsoleArr[0].toLowerCase();
            switch (commandConsole) {
                case "users":
                    resp.sendRedirect("allUsers");
                    break;
                case "books":
                    resp.sendRedirect("allBooks");
                    break;
                default:
                    resp.getWriter().println("This command false. Please try again");
            }
        } catch (IOException e) {
            log.error("Servlet Pull Exception {}", e);
        }
    }
}
