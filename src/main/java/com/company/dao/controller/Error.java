package com.company.dao.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Error implements Command {
    private static final Logger log = LogManager.getLogger(Error.class);
    @Override
    public String execude(HttpServletRequest req) {
            log.error("start method Error {}", req);
            String message = req.getParameter("error");
            return "error.jsp";
    }
}
