package com.company.service;

import com.company.entity.User;

import java.util.List;

public interface UserService extends AbstractService<User>{
    User fundUserByEmail(String email);

    List<User> findUsersByLastName(String lastName);
}
