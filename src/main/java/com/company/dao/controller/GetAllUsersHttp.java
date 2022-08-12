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

@WebServlet("/allUsers")
public class GetAllUsersHttp extends HttpServlet {
    private static final Logger log = LogManager.getLogger(GetAllUsersHttp.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Start GetAllUsersHttp {}", req.getParameter("id"));
        try (DataSourceElephant dataSourceElephant = new DataSourceElephant()) {
            UserService userService = new UserService(new UserDaoImpl(dataSourceElephant));
            List<User> users = userService.getAllUsers();
            resp.getWriter().println("<!DOCTYPE html><html><head><title>BookStore</title></head>");
            resp.getWriter().println("<body>");
            resp.getWriter().println("<h1>AllUsers (abbreviated representation): \n</h1><hr>");
            resp.getWriter().println("<table>" +
                    "      <tr>" +
                    "        <th>Id</th>" +
                    "        <th>UserName</th>" +
                    "        <th>LastName</th>" +
                    "        <th>email</th>" +
                    "        <th>role</th>" +
                    "      </tr>");
            for (int i = 0; i < users.size(); i++) {
                resp.getWriter().printf("<tr><td>%d   </td><td><a href=\"getuserbyid?id=%d\">%s</a></td>" +
                                "<td>%s</td><td>%s</td><td>%s</td></tr>",
                        i, users.get(i).getId(), users.get(i).getName(), users.get(i).getLast_name(),
                        users.get(i).getEmail(), users.get(i).getRole());
            }
            resp.getWriter().println("</table></body></html>");
        } catch (Exception e) {
            log.error("Exception by GetAllUsersHttp {}", e);
        }
    }
}
