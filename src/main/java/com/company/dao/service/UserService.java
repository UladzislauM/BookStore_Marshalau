package com.company.dao.service;

import com.company.dao.entity.User;
import com.company.dao.dao.UserDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserService {
    private final UserDao userDao;
    private static final Logger log = LogManager.getLogger(BookService.class);


    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        List<User> users = userDao.getAll();
        log.debug("Start UserService - getAllUsers: {}", users.size());
        return users;
    }

    public User getUserById(Long id) {
        log.debug("Start UserService - getUserById: {}", id);
        return userDao.getById(id);
    }

    public User getUserByEmail(String email) {
        log.debug("Start UserService - getUserByEmail: {}", email);
        return userDao.getByEmail(email);
    }

    public List<User> getUsersByLastName(String lastName) {
        log.debug("Start UserService - getUsersByLastName: {}", lastName);
        return userDao.getUserByLastName(lastName);
    }

    public boolean deleteUserById(Long id) {
        boolean checkUser = userDao.delete(id);
        log.debug("Start UserService - deleteUserById: {}", id);
        return checkUser;
    }

    public User createUser(User user) {
        log.debug("Start UserService - createUser: {}", user);
        return userDao.create(user);
    }

    public User updateUserById(User user) {
        log.debug("Start UserService - updateUserById: {}", user);
        return userDao.update(user);
    }

    public Long countAllUsers() {
        log.debug("Start UserService - countAllUsers");
        return userDao.countAllUsers();
    }
}
