package com.company.controller;

import com.company.controller.impl.CommandFactory;
import com.company.util.CustomConnectionPool;
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
        log.info("Start Controller doPost");
        forwardProcess(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Start Controller doGet");
        forwardProcess(req, resp);
    }

    private void forwardProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandParam = req.getParameter("command");
        Command command = CommandFactory.INSTANCE.getCommand(commandParam);
        String page;
        if (commandParam == null || command == null) {
            page = "JSP/index.jsp";
            log.info("Address error or Command == null");
        } else {
            try {
                page = command.execute(req);
            } catch (Exception e) {
                log.error("Controller exception, execute {}", e.getMessage(), e);
                req.setAttribute("errorMessage", "Oops..... " + e.getMessage());
                page = "JSP/error.jsp";
            }
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    public void destroy() {
        CustomConnectionPool.INSTANCE.destroyPool();
    }
}
