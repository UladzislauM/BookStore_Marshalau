package com.company.controller.impl.user;

import com.company.controller.Command;
import com.company.entity.RoleUser;
import com.company.entity.User;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserUpdate implements Command {
    private static final Logger log = LogManager.getLogger(UserFindById.class);
    private final UserService userService;

    public UserUpdate(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start UserUpdate {}", req.getParameter("id"));
        User user = userService.findById(Long.parseLong(req.getParameter("id")));
        addUserKeyHttpReq(req, user);
        userService.update(user);
        req.setAttribute("user", user);
        return "JSP/user.jsp";
    }

    private void addUserKeyHttpReq(HttpServletRequest req, User user) {
        if (req.getParameter("name") != null) {
            user.setName(req.getParameter("name"));
        }
        if (req.getParameter("last_name") != null) {
            user.setLast_name(req.getParameter("last_name"));
        }
        if (req.getParameter("email") != null) {
            user.setEmail(req.getParameter("email"));
        }
        if (req.getParameter("password") != null) {
            user.setPassword(req.getParameter("password"));
        }
        if (req.getParameter("role") != null) {
            String roleStr = req.getParameter("role");
            user.setRole(RoleUser.valueOf(roleStr));
        }
    }
}
