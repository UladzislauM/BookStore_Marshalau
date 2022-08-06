package com.company.dao.repositoty;

import com.company.dao.entity.RoleUser;
import com.company.dao.entity.User;
import com.company.dao.module.UserDao;
import com.company.dao.util.DataSourceElephant;
import com.company.dao.util.DataSourcePostgres;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class UserDaoImpl implements UserDao {
    public static final String GET_ALL = "SELECT u.id, u.name, u.last_name, u.email, u.password, r.role_name" +
            " FROM users u JOIN role r ON u.role_id = r.id";
    public static final String GET_BY_ID = "SELECT u.id, u.name, u.last_name, u.email, u.password, r.role_name " +
            "FROM users u JOIN role r ON u.role_id = r.id WHERE u.id = ?";
    public static final String GET_BY_EMAIL = "SELECT u.id, u.name, u.last_name, u.email, u.password, r.role_name " +
            "FROM users u JOIN role r ON u.role_id = r.id WHERE u.email = ?";
    public static final String GET_BY_LAST_NAME = "SELECT u.id, u.name, u.last_name, u.email, u.password, r.role_name " +
            "FROM users u JOIN role r ON u.role_id = r.id WHERE u.last_name = ?";
    public static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ?";
    public static final String ADD_USER = "INSERT INTO users (name, last_name, email," +
            " password, role_id) VALUES (?, ?, ?, ?, (SELECT id FROM role WHERE role_name = ?))";
    public static final String UPDATE_BY_ID = "UPDATE users SET name = ?, last_name = ?, email = ?," +
            "password = ?, role_id = (SELECT id FROM role WHERE role_name = ?) where Id = ?";
    public static final String COUNT_USERS = "SELECT count(*) AS total FROM users";

    private final DataSourceElephant dataSourceElephant;

    public UserDaoImpl(DataSourceElephant dataSourceElephant) {
        this.dataSourceElephant = dataSourceElephant;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = dataSourceElephant.getConnection();
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
    public List<User> getUserByLastName(String lastName) {
        List<User> users = new ArrayList<>();
        Connection connection = dataSourceElephant.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_LAST_NAME, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
//                User user = process(resultSet);
                users.add(getById(resultSet.getLong(1)));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getById(Long id) {
        return getUserBySomeParams(GET_BY_ID, ps -> {
            try {
                ps.setLong(1, id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public User getByEmail(String email) {
        return getUserBySomeParams(GET_BY_EMAIL, ps -> ps.setString(1, email));
    }

    @Override
    public User create(User user) {
        Connection connection = dataSourceElephant.getConnection();
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
            Connection connection = dataSourceElephant.getConnection();
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
            Connection connection = dataSourceElephant.getConnection();
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
            Connection connection = dataSourceElephant.getConnection();
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
        user.setRole(RoleUser.valueOf(resultSet.getString("role_name")));
        return user;
    }

    private void extractedBook(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getName());
        statement.setString(2, user.getLast_name());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getPassword());
        statement.setString(5, String.valueOf(user.getRole()));
    }

    interface StatementPreparator {
        void prepare(PreparedStatement statement) throws SQLException;
    }

    private User getUserBySomeParams(String sql, StatementPreparator preparator) {
        Connection connection = dataSourceElephant.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            preparator.prepare(statement);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return process(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
