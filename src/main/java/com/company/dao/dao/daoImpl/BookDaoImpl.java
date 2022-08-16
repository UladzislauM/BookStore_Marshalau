package com.company.dao.dao.daoImpl;

import com.company.dao.dao.BookDao;
import com.company.dao.entity.Book;
import com.company.dao.entity.StatusBook;
import com.company.dao.util.DataSourceElephant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    public static final String GET_ALL = "SELECT books.id, books.title, books.name_author, books.date_release_book, books.price," +
            " books.isbn, status.status_name FROM books JOIN status ON books.status_id = status.Id;";
    public static final String GET_BY_ID = "SELECT books.id, books.title, books.name_author, books.date_release_book, books.price, " +
            "books.isbn, status.status_name FROM books JOIN status ON books.status_id = status.Id WHERE books.id = ?";
    public static final String GET_BY_ISBN = "SELECT books.id, books.title, books.name_author, books.date_release_book, books.price, " +
            "books.isbn, status.status_name FROM books JOIN status ON books.status_id = status.Id WHERE books.isbn = ?";
    public static final String GET_BY_AUTHOR = "SELECT books.id, books.title, books.name_author, books.date_release_book, books.price, " +
            "books.isbn, status.status_name FROM books JOIN status ON books.status_id = status.Id WHERE books.name_author = ?";
    public static final String DELETE_BY_ID = "DELETE FROM books WHERE id = ?";
    public static final String ADD_BOOK = "INSERT INTO books (title, name_author, date_release_book," +
            " status_id, price, isbn) VALUES (?, ?, ?, (SELECT id FROM status WHERE status_name = ?), ?, ?)";
    public static final String UPDATE_BY_ID = "UPDATE books SET title = ?, name_author = ?, date_release_book = ?," +
            "status_id = (SELECT id FROM status WHERE status_name = ?), price = ?, isbn = ? where Id = ?;";
    public static final String COUNT_BOOKS = "SELECT count(*) AS total FROM books";

    private final DataSourceElephant dataSourceElephant;
    private static final Logger log = LogManager.getLogger(BookDaoImpl.class);

    public BookDaoImpl(DataSourceElephant dataSourceElephant) {
        this.dataSourceElephant = dataSourceElephant;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        Connection connection = dataSourceElephant.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            log.debug("The method is being executed - getAll: {}", books.size());
            while (resultSet.next()) {
                Book book = process(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            log.error("Method error - getAll: {}", e);
            throw new RuntimeException("Method error - getAll Books: {}", e);
        }
        return books;
    }

    @Override
    public Book getById(Long id) {
        Connection connection = dataSourceElephant.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID);) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            log.debug("The method is being executed - getById: {}", id);
            if (resultSet.next()) {
                return process(resultSet);
            }
        } catch (SQLException e) {
            log.error("Method error - getById: {}", e);
            throw new RuntimeException("Method error - getById: {}", e);
        }
        return new Book();
    }

    @Override
    public Book getBookByISBN(String isbn) {
        Connection connection = dataSourceElephant.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ISBN);) {
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            log.debug("The method is being executed - getBookByISBN: {}", isbn);
            if (resultSet.next()) {
                return process(resultSet);
            }
        } catch (SQLException e) {
            log.error("Method error - getBookByISBN: {}", e);
            throw new RuntimeException("Method error - getBookByISBN: {}", e);
        }
        return new Book();
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        Connection connection = dataSourceElephant.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_AUTHOR);) {
            List<Book> books = new ArrayList<>();
            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();
            log.debug("The method is being executed - getBooksByAuthor: {}", author);
            while (resultSet.next()) {
                books.add(process(resultSet));
                return books;
            }
        } catch (SQLException e) {
            log.error("Method error - getBooksByAuthor: {}", e);
            throw new RuntimeException("Method error - getBooksByAuthor: {}", e);
        }
        return null;
    }

    @Override
    public Book create(Book book) {
        Connection connection = dataSourceElephant.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_BOOK, Statement.RETURN_GENERATED_KEYS)) {
            extractedBook(book, statement);
            log.debug("The method is being executed - create book: {}", book);
            if (statement.executeUpdate() == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return getById(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            log.error("Method error - create book: {}", e);
            throw new RuntimeException("Method error - create book: {}", e);
        }
        return null;
    }

    @Override
    public Book update(Book book) {

        Connection connection = dataSourceElephant.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID)) {
            extractedBook(book, statement);
            statement.setLong(7, book.getId());
            log.debug("The method is being executed - update book: {}", book);
            if (statement.executeUpdate() == 1) {
                return getById(book.getId());
            }
        } catch (SQLException e) {
            log.error("Method error - update book: {}", e);
            throw new RuntimeException("Method error - update book: {}", e);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Connection connection = dataSourceElephant.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            log.debug("The method is being executed - delete book: {}", id);
            if (statement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            log.error("Method error - delete book: {}", e);
            throw new RuntimeException("Method error - delete book: {}", e);
        }
        return false;
    }


    @Override
    public Long countAllBooks() {
        Connection connection = dataSourceElephant.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(COUNT_BOOKS);
            log.debug("The method is being executed - countAllBooks");
            if (resultSet.next()) {
                return resultSet.getLong("total");
            }
        } catch (SQLException e) {
            log.error("Method error - countAllBooks: {}", e);
            throw new RuntimeException("Method error - countAllBooks: {}", e);
        }
        throw new RuntimeException("Exception - Null");
    }

    private Book process(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setTitle(resultSet.getString("title"));
        book.setNameAuthor(resultSet.getString("name_author"));
        book.setDateReleaseBook(resultSet.getTimestamp("date_release_book").toLocalDateTime().toLocalDate());
        book.setPrice(resultSet.getBigDecimal("price"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setStatus(StatusBook.valueOf(resultSet.getString("status_name")));
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
