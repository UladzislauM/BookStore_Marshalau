package com.company.dao.controller;

import com.company.dao.service.serviceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDelete implements Command {
    private final UserServiceImpl userServiceImpl;

    public UserDelete(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    private static final Logger log = LogManager.getLogger(UserCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start UserDelete {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            boolean checkDelete = userServiceImpl.deleteUserById(Long.parseLong(req.getParameter("id")));
            if (!checkDelete) {
                req.setAttribute("errorMessage", "The user does not deleted");
                log.error("The user does not deleted");
                return "error.jsp";
            } else {
                req.setAttribute("users", userServiceImpl.getAllUsers());
                return "users.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by UserDelete {}", e);
            req.setAttribute("errorMessage", "The user does not deleted: " + e);
            return "error.jsp";
        }
    }
}
