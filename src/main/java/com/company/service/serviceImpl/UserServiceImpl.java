package com.company.service.serviceImpl;

import com.company.dao.UserDao;
import com.company.entity.User;
import com.company.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);
    private final UserDao userDao;


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userDao.findAll();
        log.debug("Start UserService - findAllUsers: {}", users.size());
        return users;
    }

    @Override
    public User findById(Long id) {
        log.debug("Start UserService - findUserById: {}", id);
        return userDao.findById(id);
    }

    @Override
    public User fundUserByEmail(String email) {
        log.debug("Start UserService - fundUserByEmail: {}", email);
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> findUsersByLastName(String lastName) {
        log.debug("Start UserService - findUsersByLastName: {}", lastName);
        return userDao.findUserByLastName(lastName);
    }

    @Override
    public void delete(Long id) {
        if (userDao.delete(id)) {
            log.debug("Start UserService - deleteUser: {}", id);
        } else {
            log.error("UserService - deleteUser false: {}", id);
            throw new RuntimeException("DeleteUser - User is not exist...");
        }
    }

    @Override
    public User create(User user) {
        log.debug("Start UserService - createUser: {}", user);
        return userDao.create(user);
    }

    @Override
    public User update(User user) {
        log.debug("Start UserService - updateUser: {}", user);
        return userDao.update(user);
    }

    @Override
    public Long countAll() {
        log.debug("Start UserService - countAllUsers");
        return userDao.countAll();
    }
}
