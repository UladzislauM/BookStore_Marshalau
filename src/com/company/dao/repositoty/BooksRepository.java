package com.company.dao.repositoty;

import com.company.dao.base.Book;
import com.company.dao.daoImpl.UserDaoImpl;
import com.company.dao.module.UserDao;
import com.company.dao.util.DataSourse;

import java.util.ArrayList;
import java.util.List;

public class BooksRepository {
    List<Book> books = new ArrayList<>();
    UserDao userDao = new UserDaoImpl(new DataSourse());

    public void getAllBooks() {
        books = userDao.getAll();
    }
    public void getBooksById(Long id) {
        System.out.println("Start method Service.getBooksByID");
        Book book = userDao.getById(id);
        System.out.println("Book by number " + id + " :" + book);
    }
    public void deleteBookById(Long id){
        System.out.println("Start method Service.deleteBooksByID");
        if(userDao.delete(id)){
            System.out.println("Delete true");
        }else {
            System.out.println("Delete false");
        }
    }
    public void addBookById(Book book){
        System.out.println("Start method Service.addBooksByID");
        userDao.create(book);
        System.out.println(book);
    }
}
