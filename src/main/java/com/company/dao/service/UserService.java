package com.company.dao.service;

import com.company.dao.entity.User;
import com.company.dao.module.UserDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        logger.log(Level.DEBUG, "Start UserService - getAllUsers");
        List<User> users = userDao.getAll();
        if (users == null) {
            logger.log(Level.ERROR, "There are no users in the table");
            throw new RuntimeException("There are no users in the table");
        }
        return users;
    }

    public User getUserById(Long id) {
        logger.log(Level.DEBUG, "Start UserService - getUserById");
        User user = userDao.getById(id);
        if (user == null) {
            logger.log(Level.ERROR, "User not found.");
            throw new RuntimeException("User not found.");
        }
        return user;
    }

    public User getUserByEmail(String email) {
        logger.log(Level.DEBUG, "Start UserService - getUserByEmail");
        User user = userDao.getByEmail(email);
        if (user == null) {
            logger.log(Level.ERROR, "Email not found.");
            throw new RuntimeException("Email not found.");
        }
        return user;
    }

    public List<User> getUsersByLastName(String lastName) {
        logger.log(Level.DEBUG, "Start UserService - getUsersByLastName");
        List<User> users = userDao.getUserByLastName(lastName);
        if (users == null) {
            logger.log(Level.ERROR, "Last Name not found.");
            throw new RuntimeException("Last Name not found.");
        }
        return users;
    }

    public void deleteUserById(Long id) {
        logger.log(Level.DEBUG, "Start UserService - deleteUserById");
        if (!userDao.delete(id)) {
            logger.log(Level.ERROR, "Such a user cannot be deleted");
            throw new RuntimeException("Such a user cannot be deleted");
        }
    }

    public User createUser(User user) {
        logger.log(Level.DEBUG, "Start UserService - createUser");
        user = userDao.create(user);
        if (user == null) {
            logger.log(Level.ERROR, "Such a user cannot be added");
            throw new RuntimeException("Such a user cannot be added");
        }
        return user;
    }

    public User updateUserById(User user) {
        logger.log(Level.DEBUG, "Start UserService - updateUserById");
        user = userDao.update(user);
        if (user == null) {
            logger.log(Level.ERROR, "Such a user cannot be updated");
            throw new RuntimeException("Such a user cannot be updated");
        }
        return user;
    }

    public Long countAllUsers() {
        logger.log(Level.DEBUG, "Start UserService - countAllUsers");
        return userDao.countAllUsers();
    }

    static Logger logger = LogManager.getLogger();
}
