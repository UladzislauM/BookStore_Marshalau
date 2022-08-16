package com.company.dao.service;

import com.company.dao.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    List<User> getUsersByLastName(String lastName);

    boolean deleteUserById(Long id);

    User createUser(User user);

    User updateUserById(User user);

    Long countAllUsers();
}
