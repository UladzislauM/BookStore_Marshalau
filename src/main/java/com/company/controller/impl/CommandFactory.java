package com.company.controller.impl;

import com.company.controller.Command;
import com.company.controller.impl.book.*;
import com.company.controller.impl.user.*;
import com.company.dao.daoImpl.BookDaoImpl;
import com.company.dao.daoImpl.UserDaoImpl;
import com.company.service.BookService;
import com.company.service.UserService;
import com.company.service.serviceImpl.BookServiceImpl;
import com.company.service.serviceImpl.UserServiceImpl;
import com.company.util.CustomConnectionPool;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    public static final CommandFactory INSTANCE = new CommandFactory();
    public final Map<String, Command> commandMap;

    private CommandFactory() {
        commandMap = new HashMap<>();
        BookService bookService = new BookServiceImpl(new BookDaoImpl(CustomConnectionPool.INSTANCE));
        UserService userService = new UserServiceImpl(new UserDaoImpl(CustomConnectionPool.INSTANCE));

        commandMap.put("find_user_by_id", new UserFindById(userService));
        commandMap.put("users_find", new UsersFindAll(userService));
        commandMap.put("user_create", new UserCreate(userService));
        commandMap.put("user_delete", new UserDelete(userService));
        commandMap.put("user_update", new UserUpdate(userService));
        commandMap.put("user_update_form", new ToUpdatePageUser(userService));

        commandMap.put("find_book_by_id", new BookFindById(bookService));
        commandMap.put("books_find", new BooksFindAll(bookService));
        commandMap.put("book_create", new BookCreate(bookService));
        commandMap.put("book_delete", new BookDelete(bookService));
        commandMap.put("book_update", new BookUpdate(bookService));
        commandMap.put("book_update_form", new ToUpdatePageBook(bookService));

        commandMap.put("error", new Error());
    }

    public Command getCommand(String command) {
        return commandMap.get(command);
    }
}
