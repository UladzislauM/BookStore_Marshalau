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
        List<User> users = userDao.getAll();
        if(users == null) {
            throw new RuntimeException("There are no users in the table");
        }
        return users;
    }

    public User getUserById(Long id) {
        System.out.println("Start method Service.getUserById");
        User user = userDao.getById(id);
        if(user == null){
            throw new RuntimeException("User not found.");
        }
        return user;
    }

    public User getUserByEmail(String email) {
        System.out.println("Start method Service.getUserByEmail");
        User user = userDao.getByEmail(email);
        if(user == null){
            throw new RuntimeException("Email not found.");
        }
        return user;
    }
    public List<User> getUsersByLastName(String lastName) {
        System.out.println("Start method Service.getUserByEmail");
        List<User> users = userDao.getUserByLastName(lastName);
        if(users == null){
            throw new RuntimeException("Last Name not found.");
        }
        return users;
    }

    public void deleteUserById(Long id) {
        System.out.println("Start method Service.deleteUserById");
        if(!userDao.delete(id)) {
            throw new RuntimeException("Such a user cannot be deleted");
        }
    }

    public User createUser(User user) {
        System.out.println("Start method Service.createUser");
        user = userDao.create(user);
        if (user == null){
            throw new RuntimeException("Such a user cannot be added");
        }
        return user;
    }

    public User updateUserById(User user) {
        System.out.println("Start method Service.updateUserById");
        user = userDao.update(user);
        if(user == null) {
            throw new RuntimeException("Such a user cannot be updated");
        }
        return user;
    }

    public Long countAllUsers() {
        System.out.println("Start method Service.countAllUsers");
        return userDao.countAllUsers();
    }

}
