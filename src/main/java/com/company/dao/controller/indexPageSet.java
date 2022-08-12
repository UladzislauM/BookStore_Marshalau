package com.company.dao.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/index")
public class indexPageSet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(indexPageSet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        log.info("Start indexPageSet {}", id);
        try {
            resp.getWriter().println("<!DOCTYPE html><html><head><title>BookStoreIndex</title></head>");
            resp.getWriter().println("<body>\n" +
                    "<form action=\"indexPull\" method=\"POST\">" +
                    "<h4 align=\"center\">What do you want to work with?(users, books):</h4>" +
                    " <p align=\"center\">write command:</p>" +
                    "  <input type=\"text\" name=\"textPull\" value=\"\" align=\"center\">" +
                    "  <input type=\"submit\" value=\"Submit\" />" +
                    "</form></body>");
        } catch (Exception e) {
            log.error("Servlet doGet Exception, index: {}", e);
        }
    }
}
