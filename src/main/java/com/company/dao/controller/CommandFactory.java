package com.company.dao.controller;

import com.company.dao.dao.daoImpl.BookDaoImpl;
import com.company.dao.dao.daoImpl.UserDaoImpl;
import com.company.dao.entity.Book;
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
        commandMap.put("book", new BookCommand(bookServiceImpl));
        commandMap.put("books", new BooksCommand(bookServiceImpl));
        commandMap.put("users", new UsersCommand(userServiceImpl));
        commandMap.put("user", new UserCommand(userServiceImpl));
        commandMap.put("error", new Error());
        commandMap.put("user_create", new UserCreate(userServiceImpl));
        commandMap.put("book_create", new BookCreate(bookServiceImpl, book));
        commandMap.put("user_delete", new UserDelete(userServiceImpl));
        commandMap.put("book_delete", new BookDelete(bookServiceImpl));
        commandMap.put("book_update", new BookUpdate(bookServiceImpl, book));
    }

    public Command getCommand(String command) {
        return commandMap.get(command);
    }
}
