package com.company.controller.impl;

import com.company.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Error implements Command {
    private static final Logger log = LogManager.getLogger(Error.class);

    @Override
    public String execute(HttpServletRequest req) {
        log.error("Start method Error {}", req);
        return "JSP/error.jsp";
    }
}
