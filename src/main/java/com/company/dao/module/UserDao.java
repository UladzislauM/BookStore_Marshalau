package com.company.dao.module;

import com.company.dao.entity.Book;
import com.company.dao.entity.User;

import java.util.List;

public interface UserDao {
    User create(User user);

    User getById(Long id);

    Long countAllUsers();

    List<User> getAll();

    User update(User user);

    boolean delete(Long id);

    public User getByEmail(String email);
    public List<User> getUserByLastName(String lastName);

}
