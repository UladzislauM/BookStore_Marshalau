package com.company.dao;

import com.company.entity.User;

import java.util.List;

public interface UserDao extends AbstractDao<User> {
    User findByEmail(String email);

    List<User> findUserByLastName(String lastName);
}
