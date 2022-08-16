package com.company.dao.controller;

import com.company.dao.entity.User;
import com.company.dao.service.serviceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UsersCommand implements Command {
    private final UserServiceImpl userServiceImpl;

    public UsersCommand(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    private static final Logger log = LogManager.getLogger(UsersCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start UsersCommand {}", req);
        try {
            List<User> users = userServiceImpl.getAllUsers();
            req.setAttribute("users", users);
            return "users.jsp";
        } catch (Exception e) {
            log.error("Exception by UsersCommand {}", e);
            req.setAttribute("errorMessage", "Ops..... The user does not exist: " + e);
            return "error.jsp";
        }
    }
}
