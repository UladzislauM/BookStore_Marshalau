package com.company.dao.repositoty;

import com.company.dao.entity.Book;
import com.company.dao.entity.StatusBook;
import com.company.dao.module.BookDao;
import com.company.dao.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    public static final String GET_ALL = "SELECT id, title, name_author, date_release_book, status, price, isbn" +
            " FROM books";
    public static final String GET_BY_ID = "SELECT id, title, name_author, date_release_book, status, price, isbn" +
            " FROM books WHERE id = ?";
    public static final String GET_BY_ISBN = "SELECT id, title, name_author, date_release_book, status, price, isbn" +
            " FROM books WHERE isbn = ?";
    public static final String GET_BY_AUTHOR = "SELECT id, title, name_author, date_release_book, status, price, isbn" +
            " FROM books WHERE name_author = ?";
    public static final String DELETE_BY_ID = "DELETE FROM books WHERE id = ?";
    public static final String ADD_BOOK = "INSERT INTO books (title, name_author, date_release_book," +
            " status, price, isbn) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_BY_ID = "UPDATE books SET title = ?, name_author = ?, date_release_book = ?," +
            "status = ?, price = ?, isbn = ? where Id = ?;";
    public static final String COUNT_BOOKS = "SELECT count(*) AS total FROM books";

    private final DataSource dataSource;

    public BookDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                Book book = process(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book getById(Long id) {
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
    public Book getBookByISBN(String isbn) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ISBN);) {
            statement.setString(1, isbn);
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
    public List<Book> getBooksByAuthor(String author) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_AUTHOR);) {
            List<Book> books = new ArrayList<>();
            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(process(resultSet));
            }
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book create(Book book) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_BOOK, Statement.RETURN_GENERATED_KEYS)) {
            extractedBook(book, statement);
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
    public Book update(Book book) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID);
            extractedBook(book, statement);
            statement.setLong(7, book.getId());
            if (statement.executeUpdate() == 1) {
                return getById(book.getId());
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
    public Long countAllBooks() {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(COUNT_BOOKS);
            if (resultSet.next()) {
                return resultSet.getLong("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Exception - Null");
    }

    private Book process(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setTitle(resultSet.getString("title"));
        book.setNameAuthor(resultSet.getString("name_author"));
        book.setDateReleaseBook(resultSet.getTimestamp("date_release_book").toLocalDateTime().toLocalDate());
        book.setStatus(StatusBook.valueOf(resultSet.getString("status")));
        book.setPrice(resultSet.getBigDecimal("price"));
        book.setIsbn(resultSet.getString("isbn"));
        return book;
    }

    private void extractedBook(Book book, PreparedStatement statement) throws SQLException {
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getNameAuthor());
        statement.setDate(3, Date.valueOf(book.getDateReleaseBook()));
        statement.setString(4, String.valueOf(book.getStatus()));
        statement.setBigDecimal(5, book.getPrice());
        statement.setString(6, book.getIsbn());
    }
}
