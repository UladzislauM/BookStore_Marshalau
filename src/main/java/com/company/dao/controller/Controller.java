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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String commandParam = req.getParameter("command");
            log.info("Start Controller {}", commandParam);
            if (commandParam == null) {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                Command command = CommandFactory.INSTANCE.getCommand(commandParam);
                String page = command.execude(req);
                req.getRequestDispatcher(page).forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            log.error("Controller exception {}", e);
        }
    }

    @Override
    public void destroy() {
        DataSourceElephant.INSTANCE.close();
    }
}
