package com.company.dao.controller;

import com.company.dao.util.DataSourceElephant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger log = LogManager.getLogger(Controller.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandParam = req.getParameter("post");
        log.info("Start Controller doPost {}", commandParam);
        try {
            if (commandParam == null) {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                log.info("Address error or CommandParam == null");
            } else {
                Command command = CommandFactory.INSTANCE.getCommand(commandParam);
                if (command == null) {
                    req.getRequestDispatcher("error.jsp").forward(req, resp);
                    log.error("Input error");
                } else {
                    String page = "";
                    try {
                        page = command.execude(req);
                    } catch (Exception e) {
                        log.error("Controller exception, execude {}", e);
                        req.setAttribute("errorMessage", "Oops ...");
                        page = "error.jsp";
                    }
                    req.getRequestDispatcher(page).forward(req, resp);
                }
            }
        } catch (ServletException | IOException e) {
            log.error("Controller exception, forward {}", e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String commandParam = req.getParameter("command");
        log.info("Start Controller doGet {}", commandParam);
        try {
            if (commandParam == null) {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                log.info("Address error or CommandParam == null");
            } else {
                Command command = CommandFactory.INSTANCE.getCommand(commandParam);
                if (command == null) {
                    req.getRequestDispatcher("error.jsp").forward(req, resp);
                    log.error("Input error");
                } else {
                    String page = "";
                    try {
                        page = command.execude(req);
                    } catch (Exception e) {
                        log.error("Controller exception, execude {}", e);
                        req.setAttribute("errorMessage", "Oops ...");
                        page = "error.jsp";
                    }
                    req.getRequestDispatcher(page).forward(req, resp);
                }
            }
        } catch (ServletException | IOException e) {
            log.error("Controller exception, forward {}", e);
        }
    }

    @Override
    public void destroy() {
        DataSourceElephant.INSTANCE.close();
    }
}
