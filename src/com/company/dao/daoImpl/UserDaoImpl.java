package com.company.dao.daoImpl;

import com.company.dao.base.Book;
import com.company.dao.module.UserDao;
import com.company.dao.util.DataSourse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    public static final String GET_ALL = "SELECT id, bookname, nameaytor, datepurchase, status, price" +
            " FROM bookstore_bh";
    public static final String GET_BY_ID = "SELECT id, bookname, nameaytor, datepurchase, status, price" +
            " FROM bookstore_bh WHERE id = ?";
    public static final String DELETE_BY_ID = "DELETE FROM bookstore_bh WHERE id = ?";
    public static final String ADD_USER = "INSERT INTO bookstore_bh (bookname, nameaytor, datepurchase," +
            " status, price) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_BY_ID = "UPDATE bookstore_bh SET ? = ? where Id = ?;";
    //    public static final String GET_BY_NAME = "SELECT id, name, lastname, birthdate," +
//            " bookname, nameaytor, datepurchase FROM bookstore_bh WHERE id = ?";
    private final DataSourse dataSourse;

    public UserDaoImpl(DataSourse dataSourse) {
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
                book.setNameaytor(resultSet.getString("nameaytor"));
                book.setDatepurchase(resultSet.getDate("datepurchase"));
                book.setStatus(resultSet.getString("status"));
                book.setPrice(resultSet.getInt("price"));

                book.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            connection.close();
//        }

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
                book.setNameaytor(resultSet.getString("nameaytor"));
                book.setDatepurchase(resultSet.getDate("datepurchase"));
                book.setStatus(resultSet.getString("status"));
                book.setPrice(resultSet.getInt("price"));

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
            statement.setString(2, book.getNameaytor());
            statement.setDate(3, book.getDatepurchase());
            statement.setString(4, book.getStatus());
            statement.setInt(5, book.getPrice());

//            int count = statement.executeUpdate();

            return book;
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
//передать, сравнить, выделить разное, добавить
            statement.setString(1, book.getBookname());
            statement.setString(2, book.getNameaytor());
            statement.setDate(3, book.getDatepurchase());

//            int count = statement.executeUpdate();

            return book;
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
}
