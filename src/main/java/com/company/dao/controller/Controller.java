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
        forwardProcess(req, resp, commandParam);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String commandParam = req.getParameter("command");
        log.info("Start Controller doGet {}", commandParam);
        forwardProcess(req, resp, commandParam);
    }

    private void forwardProcess(HttpServletRequest req, HttpServletResponse resp, String commandParam) {
        try {
            if (commandParam == null) {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                log.info("Address error or CommandParam == null");
            } else {
                String page = "";
                Command command = CommandFactory.INSTANCE.getCommand(commandParam);
                if (command == null) {
                    log.error("Controller exception, execude {}");
                    req.setAttribute("errorMessage", "Oops..... You entered the wrong command.....");
                    page = "error.jsp";
                } else {
                    try {
                        page = command.execude(req);
                    } catch (Exception e) {
                        log.error("Controller exception, execude {}", e);
                        req.setAttribute("errorMessage", "Oops..... Page not found.....");
                        page = "error.jsp";
                    }
                }
                req.getRequestDispatcher(page).forward(req, resp);
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
