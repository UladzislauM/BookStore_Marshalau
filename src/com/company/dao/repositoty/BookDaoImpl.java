package com.company.dao.repositoty;

import com.company.dao.base.Book;
import com.company.dao.module.BookDao;
import com.company.dao.util.DataSourse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    public static final String GET_ALL = "SELECT id, bookname, nameauthor, datepurchase, status, price, isbn" +
            " FROM bookstore_bh";
    public static final String GET_BY_ID = "SELECT id, bookname, nameauthor, datepurchase, status, price, isbn" +
            " FROM bookstore_bh WHERE id = ?";
    public static final String DELETE_BY_ID = "DELETE FROM bookstore_bh WHERE id = ?";
    public static final String ADD_USER = "INSERT INTO bookstore_bh (bookname, nameauthor, datepurchase," +
            " status, price, isbn) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_BY_ID = "UPDATE bookstore_bh SET bookname = ?, nameauthor = ?, datepurchase = ?," +
            "status = ?, price = ?, isbn = ? where Id = ?;";
    public static final String GET_BY_ISBN = "SELECT id, bookname, nameauthor, datepurchase, status, price, isbn" +
            " FROM bookstore_bh WHERE isbn = ?";

    private final DataSourse dataSourse;

    public BookDaoImpl(DataSourse dataSourse) {
        this.dataSourse = dataSourse;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = dataSourse.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setBookname(resultSet.getString("bookname"));
                book.setNameauthor(resultSet.getString("nameauthor"));
                book.setDatepurchase(resultSet.getDate("datepurchase"));
                book.setStatus(resultSet.getString("status"));
                book.setPrice(resultSet.getInt("price"));
                book.setIsbn(resultSet.getInt("isbn"));

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book getById(Long id) {
        try {
            Connection connection = dataSourse.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setBookname(resultSet.getString("bookname"));
                book.setNameauthor(resultSet.getString("nameauthor"));
                book.setDatepurchase(resultSet.getDate("datepurchase"));
                book.setStatus(resultSet.getString("status"));
                book.setPrice(resultSet.getInt("price"));
                book.setIsbn(resultSet.getInt("isbn"));

                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book create(Book book) {
        try {
            Connection connection = dataSourse.getConnection();
            PreparedStatement statement = connection.prepareStatement(ADD_USER);

            statement.setString(1, book.getBookname());
            statement.setString(2, book.getNameauthor());
            statement.setDate(3, book.getDatepurchase());
            statement.setString(4, book.getStatus());
            statement.setInt(5, book.getPrice());
            statement.setInt(6, book.getIsbn());

            if (statement.executeUpdate() == 1) {
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Book update(Book book) {
        try {
            Connection connection = dataSourse.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID);

            statement.setString(1, book.getBookname());
            statement.setString(2, book.getNameauthor());
            statement.setDate(3, book.getDatepurchase());
            statement.setString(4, book.getStatus());
            statement.setInt(5, book.getPrice());
            statement.setInt(6, book.getIsbn());
            statement.setLong(7, book.getId());

            if (statement.executeUpdate() == 1) {
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean delete(Long id) {
        try {
            Connection connection = dataSourse.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setLong(1, id);
            System.out.println(statement.executeUpdate());
            int count = statement.executeUpdate();
            if (count == 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Book getBookByISBN(Integer isbn) {
        try {
            Connection connection = dataSourse.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_BY_ISBN);
            statement.setLong(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setBookname(resultSet.getString("bookname"));
                book.setNameauthor(resultSet.getString("nameauthor"));
                book.setDatepurchase(resultSet.getDate("datepurchase"));
                book.setStatus(resultSet.getString("status"));
                book.setPrice(resultSet.getInt("price"));
                book.setIsbn(resultSet.getInt("isbn"));

                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
