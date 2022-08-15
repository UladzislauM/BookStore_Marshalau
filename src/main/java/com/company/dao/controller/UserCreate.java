package com.company.dao.controller;

import com.company.dao.entity.RoleUser;
import com.company.dao.entity.User;
import com.company.dao.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserCreate implements Command {
    private final UserService userService;

    public UserCreate(UserService userService) {
        this.userService = userService;
    }

    private static final Logger log = LogManager.getLogger(UserCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start UserCreate {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            User user = addUserKeyHttpReq(req);
            if (user.getName() == null) {
                req.setAttribute("errorMessage", "The user does not exist");
                log.error("The user does not exist");
                return "error.jsp";
            } else {
                userService.createUser(user);
                req.setAttribute("users", userService.getAllUsers());
                return "users.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by UserCreate {}", e);
            req.setAttribute("errorMessage", "The user does not exist: " + e);
            return "error.jsp";
        }
    }

    private User addUserKeyHttpReq(HttpServletRequest req) {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setLast_name(req.getParameter("last_name"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        String roleStr = req.getParameter("role");
        user.setRole(RoleUser.valueOf(roleStr));
        return user;
    }
}
