package com.company.dao.base;

import java.sql.Date;
import java.util.Objects;

public class Book {
    private Long id;
    private String bookname;
    private String nameaytor;
    private Date datepurchase;
    private  String status;
    private Integer price;

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

    public String getNameaytor() {
        return nameaytor;
    }

    public void setNameaytor(String nameaytor) {
        this.nameaytor = nameaytor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(bookname, book.bookname) && Objects.equals(nameaytor, book.nameaytor) && Objects.equals(datepurchase, book.datepurchase) && Objects.equals(status, book.status) && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookname, nameaytor, datepurchase, status, price);
    }

    @Override
    public String toString() {
        return "BookStore{" +
                "id=" + id +
                ", bookname='" + bookname + '\'' +
                ", nameaytor='" + nameaytor + '\'' +
                ", datepurchase=" + datepurchase +
                ", status='" + status + '\'' +
                ", price=" + price +
                '}';
    }
}
