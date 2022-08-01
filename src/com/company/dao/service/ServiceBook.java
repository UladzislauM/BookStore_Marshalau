package com.company.dao.service;

import com.company.dao.base.Book;
import com.company.dao.module.BookDao;
import com.company.dao.repositoty.BookDaoImpl;
import com.company.dao.util.DataSourse;

import java.util.List;

public class ServiceBook {
    BookDao bookDao = new BookDaoImpl(new DataSourse());

    public void getAllBooks() {
        System.out.println("Start method Service.GetAllBooks");
        List<Book> books = bookDao.getAll();
        System.out.println("AllBooks: ");
        for (int i = 0; i < books.size() - 1; i++) {
            System.out.printf("ID: %d, BookName: %s, NameAuthor: %s, DataPurchase: %td-%<tm-%<tyy %n",
                    books.get(i).getId(), books.get(i).getBookname(), books.get(i).getNameauthor(),
                    books.get(i).getDatepurchase());
        }
    }
    public void getBookById(Long id) {
        System.out.println("Start method Service.getBooksByID");
        Book book = bookDao.getById(id);
        System.out.println("Book by number " + id + " :" + book);
    }
    public void deleteBookById(Long id){
        System.out.println("Start method Service.deleteBooksByID");
        if(bookDao.delete(id)){
            System.out.println("Delete true");
        }else {
            System.out.println("Delete false");
        }
    }
    public void addBookById(Book book){
        System.out.println("Start method Service.addBooksByID");
        System.out.println(bookDao.create(book));
    }
    public void updateBookById(Book book){
        System.out.println("Start method Service.updateBookById");
        bookDao.update(book);
        System.out.println(book);
    }

    public Book getBookByISBN(Integer isbn) {
        System.out.println("Start method Service.getBookByISBN");
        Book book = bookDao.getBookByISBN(isbn);
        System.out.println("Book by isbn " + isbn + " :" + book);
        return book;
    }
}
