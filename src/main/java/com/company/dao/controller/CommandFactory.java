package com.company.dao.controller;

import com.company.dao.dao.daoImpl.BookDaoImpl;
import com.company.dao.dao.daoImpl.UserDaoImpl;
import com.company.dao.service.BookService;
import com.company.dao.service.UserService;
import com.company.dao.util.DataSourceElephant;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    public static final CommandFactory INSTANCE = new CommandFactory();
    public final Map<String, Command> commandMap;

    private CommandFactory() {
        commandMap = new HashMap<>();
        BookService bookService = new BookService(new BookDaoImpl(DataSourceElephant.INSTANCE));
        UserService userService = new UserService(new UserDaoImpl(DataSourceElephant.INSTANCE));
        commandMap.put("book", new BookCommand(bookService));
        commandMap.put("books", new BooksCommand(bookService));
        commandMap.put("users", new UsersCommand(userService));
        commandMap.put("user", new UserCommand(userService));
        commandMap.put("error", new Error());
        commandMap.put("user_create", new UserCreate(userService));
        commandMap.put("user_delete", new UserDelete(userService));
    }

    public Command getCommand(String command) {
        return commandMap.get(command);
    }
}
