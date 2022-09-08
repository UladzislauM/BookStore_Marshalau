package com.company.dao.daoImpl;

import com.company.dao.UserDao;
import com.company.entity.RoleUser;
import com.company.entity.User;
import com.company.util.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    private final CustomConnectionPool customConnectionPool;
    private static final Logger log = LogManager.getLogger(BookDaoImpl.class);

    public UserDaoImpl(CustomConnectionPool customConnectionPool) {
        this.customConnectionPool = customConnectionPool;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = customConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                User user = process(resultSet);
                users.add(user);
            }
            log.debug("The method is being executed - getAll: {}", users.size());
        } catch (SQLException e) {
            log.error("Method error - getAll: {}", e.getMessage(), e);
            throw new RuntimeException("Method error - getAll users: {}", e);
        }
        return users;
    }

    @Override
    public List<User> findUserByLastName(String lastName) {
        List<User> users = new ArrayList<>();
        try (Connection connection = customConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_LAST_NAME, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            log.debug("The method is being executed - getUserByLastName: {}", lastName);
            while (resultSet.next()) {
                users.add(findById(resultSet.getLong(1)));
            }
        } catch (SQLException e) {
            log.error("Method error - getUserByLastName: {}", e.getMessage(), e);
            throw new RuntimeException("Method error - getUserByLastName: ", e);
        }
        return users;
    }

    @Override
    public User findById(Long id) {
        return getUserBySomeParams(GET_BY_ID, ps -> {
            try {
                ps.setLong(1, id);
                log.debug("The method is being executed - getById: {}", id);
            } catch (SQLException e) {
                log.error("Method error - getById: {}", e.getMessage(), e);
                throw new RuntimeException("Method error - getById: ", e);
            }
        });
    }

    @Override
    public User findByEmail(String email) {
        log.debug("The method is being executed - getByEmail: {}", email);
        return getUserBySomeParams(GET_BY_EMAIL, ps -> ps.setString(1, email));
    }

    @Override
    public User create(User user) {
        try (Connection connection = customConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS)) {
            extractedBook(user, statement);
            log.debug("The method is being executed - create user: {}", user);
            if (statement.executeUpdate() == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return findById(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            log.error("Method error - create user: {}", e.getMessage(), e);
            throw new RuntimeException("Method error - create user:", e);
        }
        return null;
    }

    @Override
    public User update(User user) {
        try (Connection connection = customConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID)) {
            extractedBook(user, statement);
            statement.setLong(6, user.getId());
            log.debug("The method is being executed - update user: {}", user);
            if (statement.executeUpdate() == 1) {
                return findById(user.getId());
            }
        } catch (SQLException e) {
            log.error("Method error - update user: {}", e.getMessage(), e);
            throw new RuntimeException("Method error - update user:", e);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = customConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            log.debug("The method is being executed - delete user: {}", id);
            if (statement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            log.error("Method error - delete user: {}", e.getMessage(), e);
            throw new RuntimeException("Method error - delete user:", e);
        }
        return false;
    }

    @Override
    public Long countAll() {
        try (Connection connection = customConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(COUNT_USERS);
            log.debug("The method is being executed - countAllUsers");
            if (resultSet.next()) {
                return resultSet.getLong("total");
            }
        } catch (SQLException e) {
            log.error("Method error - countAllUsers: {}", e.getMessage());
            throw new RuntimeException("Method error - countAllUsers");
        }
        return null;
    }

    private User getUserBySomeParams(String sql, StatementPreparator preparator) {
        try (Connection connection = customConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            preparator.prepare(statement);
            ResultSet resultSet = statement.executeQuery();
            log.debug("The method is being executed - getUserBySomeParams");
            if (resultSet.next()) {
                return process(resultSet);
            }
        } catch (SQLException e) {
            log.error("Method error - getUserBySomeParams: {}", e.getMessage(), e);
            throw new RuntimeException("Method error - getAll users: ", e);
        }
        return null;
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
}
