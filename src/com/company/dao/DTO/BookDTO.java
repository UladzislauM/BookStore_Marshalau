package com.company.dao.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class BookDTO {
    private Long id;
    private String title;
    private String nameAuthor;
    private LocalDate dateReleaseBook;
    private String status;
    private BigDecimal price;
    private String isbn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public LocalDate getDateReleaseBook() {
        return dateReleaseBook;
    }

    public void setDateReleaseBook(LocalDate dateReleaseBook) {
        this.dateReleaseBook = dateReleaseBook;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id) && Objects.equals(title, bookDTO.title) && Objects.equals(nameAuthor, bookDTO.nameAuthor) && Objects.equals(dateReleaseBook, bookDTO.dateReleaseBook) && Objects.equals(status, bookDTO.status) && Objects.equals(price, bookDTO.price) && Objects.equals(isbn, bookDTO.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, nameAuthor, dateReleaseBook, status, price, isbn);
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", nameAuthor='" + nameAuthor + '\'' +
                ", dateReleaseBook=" + dateReleaseBook +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
