package com.company.dao.controller;

import com.company.dao.entity.User;
import com.company.dao.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDelete implements Command {
    private final UserService userService;

    public UserDelete(UserService userService) {
        this.userService = userService;
    }

    private static final Logger log = LogManager.getLogger(UserCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start UserDelete {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            boolean checkDelete = userService.deleteUserById(Long.parseLong(req.getParameter("id")));
            if (!checkDelete) {
                req.setAttribute("error", "The user does not deleted");
                log.error("The user does not deleted");
                return "error.jsp";
            } else {
                req.setAttribute("users", userService.getAllUsers());
                return "users.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by UserDelete {}", e);
            req.setAttribute("error", "The user does not deleted: " + e);
            log.error("The user does not deleted");
            return "error.jsp";
        }
    }
}
