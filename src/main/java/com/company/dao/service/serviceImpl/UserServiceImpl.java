package com.company.dao.service.serviceImpl;

import com.company.dao.entity.User;
import com.company.dao.dao.UserDao;
import com.company.dao.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private static final Logger log = LogManager.getLogger(BookBookServiceImpl.class);


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userDao.getAll();
        log.debug("Start UserService - getAllUsers: {}", users.size());
        return users;
    }

    @Override
    public User getUserById(Long id) {
        log.debug("Start UserService - getUserById: {}", id);
        return userDao.getById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        log.debug("Start UserService - getUserByEmail: {}", email);
        return userDao.getByEmail(email);
    }

    @Override
    public List<User> getUsersByLastName(String lastName) {
        log.debug("Start UserService - getUsersByLastName: {}", lastName);
        return userDao.getUserByLastName(lastName);
    }

    @Override
    public boolean deleteUserById(Long id) {
        boolean checkUser = userDao.delete(id);
        log.debug("Start UserService - deleteUserById: {}", id);
        return checkUser;
    }

    @Override
    public User createUser(User user) {
        log.debug("Start UserService - createUser: {}", user);
        return userDao.create(user);
    }

    @Override
    public User updateUser(User user) {
        log.debug("Start UserService - updateUserById: {}", user);
        return userDao.update(user);
    }

    @Override
    public Long countAllUsers() {
        log.debug("Start UserService - countAllUsers");
        return userDao.countAllUsers();
    }
}
