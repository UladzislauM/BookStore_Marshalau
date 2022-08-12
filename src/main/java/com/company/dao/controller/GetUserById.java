package com.company.dao.controller;

import com.company.dao.dao.daoImpl.UserDaoImpl;
import com.company.dao.entity.User;
import com.company.dao.service.UserService;
import com.company.dao.util.DataSourceElephant;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@WebServlet("/getuserbyid")
public class GetUserById extends HttpServlet {
    private static final Logger log = LogManager.getLogger(GetUserById.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Start GetUserById {}", req.getParameter("id"));
        try (DataSourceElephant dataSourceElephant = new DataSourceElephant()) {
            UserService userService = new UserService(new UserDaoImpl(dataSourceElephant));
            List<User> users = userService.getAllUsers();
            resp.getWriter().println("<!DOCTYPE html><html><head><title>BookStore</title></head>");
            resp.getWriter().println("<body>");
            resp.getWriter().printf("<h1>%s %s</h1><hr>",
                    userService.getUserById(Long.parseLong(req.getParameter("id"))).getName(),
                    userService.getUserById(Long.parseLong(req.getParameter("id"))).getLast_name());
            resp.getWriter().printf("<p>* Role: %s</p>" +
                            "<p>* Email: %s</p>" +
                            "<p>* Password: %s</p>",
                    userService.getUserById(Long.parseLong(req.getParameter("id"))).getRole(),
                    userService.getUserById(Long.parseLong(req.getParameter("id"))).getEmail(),
                    userService.getUserById(Long.parseLong(req.getParameter("id"))).getPassword());
            resp.getWriter().println("</body></html>");
        } catch (Exception e) {
            log.error("Exception by GetUserById {}", e);
        }
    }
}
