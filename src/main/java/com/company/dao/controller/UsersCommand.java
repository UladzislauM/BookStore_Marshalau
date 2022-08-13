package com.company.dao.controller;

import com.company.dao.entity.User;
import com.company.dao.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UsersCommand implements Command {
    private final UserService userService;

    public UsersCommand(UserService userService) {
        this.userService = userService;
    }

    private static final Logger log = LogManager.getLogger(UsersCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start UsersCommand {}", req);
        try {
            List<User> users = userService.getAllUsers();
            req.setAttribute("users", users);
        } catch (Exception e) {
            log.error("Exception by UsersCommand {}", e);
        }
        return "users.jsp";
    }
}
