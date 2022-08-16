package com.company.dao.controller;

import com.company.dao.controller.BookControllers.*;
import com.company.dao.controller.UserControllers.*;
import com.company.dao.dao.daoImpl.BookDaoImpl;
import com.company.dao.dao.daoImpl.UserDaoImpl;
import com.company.dao.entity.Book;
import com.company.dao.entity.User;
import com.company.dao.service.serviceImpl.BookBookServiceImpl;
import com.company.dao.service.serviceImpl.UserServiceImpl;
import com.company.dao.util.DataSourceElephant;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    public static final CommandFactory INSTANCE = new CommandFactory();
    public final Map<String, Command> commandMap;

    private CommandFactory() {
        commandMap = new HashMap<>();
        BookBookServiceImpl bookServiceImpl = new BookBookServiceImpl(new BookDaoImpl(DataSourceElephant.INSTANCE));
        UserServiceImpl userServiceImpl = new UserServiceImpl(new UserDaoImpl(DataSourceElephant.INSTANCE));
        Book book = new Book();
        User user = new User();

        commandMap.put("user", new UserCommand(userServiceImpl, user));
        commandMap.put("users", new UsersCommand(userServiceImpl));
        commandMap.put("user_create", new UserCreate(userServiceImpl, user));
        commandMap.put("user_delete", new UserDelete(userServiceImpl));
        commandMap.put("user_update", new UserUpdate(userServiceImpl, user));

        commandMap.put("book", new BookCommand(bookServiceImpl, book));
        commandMap.put("books", new BooksCommand(bookServiceImpl));
        commandMap.put("book_create", new BookCreate(bookServiceImpl, book));
        commandMap.put("book_delete", new BookDelete(bookServiceImpl));
        commandMap.put("book_update", new BookUpdate(bookServiceImpl, book));

        commandMap.put("error", new Error());
    }

    public Command getCommand(String command) {
        return commandMap.get(command);
    }
}
