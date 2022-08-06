package com.company.dao.repositoty;

import com.company.dao.entity.RoleUser;
import com.company.dao.entity.User;
import com.company.dao.module.UserDao;
import com.company.dao.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    public static final String GET_ALL = "SELECT id, name, last_name, email, password, role" +
            " FROM users";
    public static final String GET_BY_ID = "SELECT id, name, last_name, email, password, role" +
            " FROM books WHERE id = ?";
     public static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ?";
    public static final String ADD_USER = "INSERT INTO books (name, last_name, email," +
            " password, role) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_BY_ID = "UPDATE books SET name = ?, last_name = ?, email = ?," +
            "password = ?, role = ? where Id = ?";
    public static final String COUNT_USERS = "SELECT count(*) AS total FROM users";

    private final DataSource dataSource;

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

   @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                User user = process(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getById(Long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID);) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return process(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

     @Override
    public User create(User user) {
         Connection connection = dataSource.getConnection();
         try (PreparedStatement statement = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS)) {
            extractedBook(user, statement);
            if (statement.executeUpdate() == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return getById(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(User user) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID);
            extractedBook(user, statement);
            statement.setLong(6, user.getId());
            if (statement.executeUpdate() == 1) {
                return getById(user.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setLong(1, id);
            if (statement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Long countAllUsers() {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(COUNT_USERS);
            if (resultSet.next()) {
                return resultSet.getLong("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Exception - Null");
    }

    private User process(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setLast_name(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(RoleUser.valueOf(resultSet.getString("role")));
        return user;
    }

    private void extractedBook(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getName());
        statement.setString(2, user.getLast_name());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getPassword());
        statement.setString(5, String.valueOf(user.getRole()));
    }
}
