package com.company.dao.controller.UserControllers;

import com.company.dao.controller.Command;
import com.company.dao.entity.RoleUser;
import com.company.dao.entity.User;
import com.company.dao.service.serviceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserCreate implements Command {
    private final UserServiceImpl userServiceImpl;
    private User user;

    public UserCreate(UserServiceImpl userServiceImpl, User user) {
        this.userServiceImpl = userServiceImpl;
        this.user = user;
    }
    private static final Logger log = LogManager.getLogger(UserCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start UserCreate {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            user = addUserKeyHttpReq(req);
            if (user.getName() == null) {
                req.setAttribute("errorMessage", "The user does not exist");
                log.error("The user does not exist");
                return "error.jsp";
            } else {
                userServiceImpl.createUser(user);
                req.setAttribute("users", userServiceImpl.getAllUsers());
                return "users.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by UserCreate {}", e);
            req.setAttribute("errorMessage", "The user does not exist: " + e);
            return "error.jsp";
        }
    }

    private User addUserKeyHttpReq(HttpServletRequest req) {
        user.setName(req.getParameter("name"));
        user.setLast_name(req.getParameter("last_name"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        String roleStr = req.getParameter("role");
        user.setRole(RoleUser.valueOf(roleStr));
        return user;
    }
}
