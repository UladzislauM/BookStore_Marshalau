package com.company.controller.impl.user;

import com.company.controller.Command;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UsersFindAll implements Command {
    private final UserService userService;

    public UsersFindAll(UserService userService) {
        this.userService = userService;
    }

    private static final Logger log = LogManager.getLogger(UsersFindAll.class);

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start UsersCommand {}", req);
        req.setAttribute("user_count", userService.countAll());
        req.setAttribute("users", userService.findAll());
        return "JSP/users.jsp";
    }
}
