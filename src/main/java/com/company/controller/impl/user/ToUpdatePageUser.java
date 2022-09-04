package com.company.controller.impl.user;

import com.company.controller.Command;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToUpdatePageUser implements Command {
    private static final Logger log = LogManager.getLogger(UserFindById.class);
    private final UserService userService;

    public ToUpdatePageUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start ToUpdatePageUser {}", req.getParameter("id"));
        req.setAttribute("user", userService.findById(Long.parseLong(req.getParameter("id"))));
        return "JSP/updateUser.jsp";
    }
}
