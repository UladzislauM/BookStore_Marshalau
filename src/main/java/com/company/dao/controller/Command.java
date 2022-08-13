package com.company.dao.controller;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    String execude(HttpServletRequest req);
}
