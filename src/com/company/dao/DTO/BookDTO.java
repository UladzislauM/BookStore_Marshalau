package com.company.dao.DTO;

import com.company.dao.entity.StatusBook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class BookDTO {
    private Long id;
    private String title;
    private String nameAuthor;
    private LocalDate dateReleaseBook;
    private StatusBookDTO statusBook;
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

    public StatusBookDTO getStatus() {
        return statusBook;
    }

    public void setStatus(StatusBookDTO statusBook) {
        this.statusBook = statusBook;
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
        return Objects.equals(id, bookDTO.id) && Objects.equals(title, bookDTO.title) && Objects.equals(nameAuthor, bookDTO.nameAuthor) && Objects.equals(dateReleaseBook, bookDTO.dateReleaseBook) && statusBook == bookDTO.statusBook && Objects.equals(price, bookDTO.price) && Objects.equals(isbn, bookDTO.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, nameAuthor, dateReleaseBook, statusBook, price, isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", nameAuthor='" + nameAuthor + '\'' +
                ", bookReleaseDate=" + dateReleaseBook +
                ", status='" + statusBook + '\'' +
                ", price=" + price +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
