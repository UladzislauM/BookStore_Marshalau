package com.company.dao.service;

import com.company.dao.entity.User;
import com.company.dao.module.UserDao;

import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        System.out.println("Start method Service.getAllUsers");
        return userDao.getAll();
    }

    public User getUserById(Long id) {
        System.out.println("Start method Service.getUserById");
        return userDao.getById(id);
    }

    public boolean deleteUserById(Long id) {
        System.out.println("Start method Service.deleteUserById");
        return userDao.delete(id);
    }

    public User createUser(User user) {
        System.out.println("Start method Service.createUser");
        return userDao.create(user);
    }

    public User updateUserById(User user) {
        System.out.println("Start method Service.updateUserById");
        return userDao.update(user);
    }

    public Long countAllUsers() {
        System.out.println("Start method Service.countAllUsers");
        return userDao.countAllUsers();
    }

}
