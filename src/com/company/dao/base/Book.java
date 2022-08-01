package com.company.dao.base;

import java.sql.Date;
import java.util.Objects;

public class Book {
    private Long id;
    private String bookname;
    private String nameauthor;
    private Date datepurchase;
    private  String status;
    private Integer price;
    private Integer isbn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getNameauthor() {
        return nameauthor;
    }

    public void setNameauthor(String nameauthor) {
        this.nameauthor = nameauthor;
    }

    public Date getDatepurchase() {
        return datepurchase;
    }

    public void setDatepurchase(Date datepurchase) {
        this.datepurchase = datepurchase;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(bookname, book.bookname) && Objects.equals(nameauthor, book.nameauthor) && Objects.equals(datepurchase, book.datepurchase) && Objects.equals(status, book.status) && Objects.equals(price, book.price) && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookname, nameauthor, datepurchase, status, price, isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookname='" + bookname + '\'' +
                ", nameauthor='" + nameauthor + '\'' +
                ", datepurchase=" + datepurchase +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", isbn=" + isbn +
                '}';
    }
}
