package com.company.dao.controller.UserControllers;

import com.company.dao.controller.Command;
import com.company.dao.entity.RoleUser;
import com.company.dao.entity.User;
import com.company.dao.service.serviceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserUpdate implements Command {
    private final UserServiceImpl userServiceImpl;
    private User user;

    public UserUpdate(UserServiceImpl userServiceImpl, User user) {
        this.userServiceImpl = userServiceImpl;
        this.user = user;
    }

    private static final Logger log = LogManager.getLogger(UserCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start UserUpdate {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            user = userServiceImpl.getUserById(Long.parseLong(req.getParameter("id")));
            user = addUserKeyHttpReq(req);
            if (user.getName() == null) {
                req.setAttribute("errorMessage", "The user update fail");
                log.error("The user update fail");
                return "error.jsp";
            } else {
                userServiceImpl.updateUser(user);
                req.setAttribute("users", userServiceImpl.getAllUsers());
                return "users.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by UserUpdate {}", e);
            req.setAttribute("errorMessage", "The user update fail: " + e);
            return "error.jsp";
        }
    }

    private User addUserKeyHttpReq(HttpServletRequest req) {
        if(req.getParameter("name") != null) {
            user.setName(req.getParameter("name"));
        }
        if(req.getParameter("last_name") != null) {
            user.setLast_name(req.getParameter("last_name"));
        }
        if (req.getParameter("email") != null) {
            user.setEmail(req.getParameter("email"));
        }
        if(req.getParameter("password") != null) {
            user.setPassword(req.getParameter("password"));
        }
        if(req.getParameter("role") != null) {
            String roleStr = req.getParameter("role");
            user.setRole(RoleUser.valueOf(roleStr));
        }
        return user;
    }
}
