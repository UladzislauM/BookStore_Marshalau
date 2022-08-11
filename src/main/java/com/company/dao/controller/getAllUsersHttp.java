package com.company.dao.controller;

import com.company.dao.dao.daoImpl.BookDaoImpl;
import com.company.dao.dao.daoImpl.UserDaoImpl;
import com.company.dao.entity.Book;
import com.company.dao.entity.User;
import com.company.dao.service.BookService;
import com.company.dao.service.UserService;
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

@WebServlet("/allUsers")
public class getAllUsersHttp extends HttpServlet {
    private static final Logger log = LogManager.getLogger(getAllUsersHttp.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        log.info("Start getAllUsersHttp {}", id);
        try (DataSourceElephant dataSourceElephant = new DataSourceElephant()) {
            UserService userService = new UserService(new UserDaoImpl(dataSourceElephant));
            List<User> users = userService.getAllUsers();
            resp.getWriter().println("AllUsers (abbreviated representation): \n");
            for (int i = 0; i < users.size(); i++) {
                resp.getWriter().printf("ID: %d, UserName: %s, LastName: %s, email: %s, role: %s\n",
                        users.get(i).getId(), users.get(i).getName(), users.get(i).getLast_name(),
                        users.get(i).getEmail(), users.get(i).getRole());
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }
}
