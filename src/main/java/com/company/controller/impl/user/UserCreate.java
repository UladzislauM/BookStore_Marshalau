package com.company.controller.impl.user;

import com.company.controller.Command;
import com.company.entity.RoleUser;
import com.company.entity.User;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserCreate implements Command {
    private static final Logger log = LogManager.getLogger(UserFindById.class);
    private final UserService userService;

    public UserCreate(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start UserCreate {}", req.getParameter("id"));
        User user = addUserKeyHttpReq(req);
        userService.create(user);
        req.setAttribute("user_count", userService.countAll());
        req.setAttribute("users", userService.findAll());
        return "JSP/users.jsp";
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
