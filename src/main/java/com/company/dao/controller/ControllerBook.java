package com.company.dao.controller;

import com.company.dao.entity.Book;
import com.company.dao.entity.StatusBook;
import com.company.dao.repositoty.BookDaoImpl;
import com.company.dao.service.BookService;
import com.company.dao.util.DataSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ControllerBook {
    public void consoleInterface() {
        try (DataSource dataSource = new DataSource()) {
            Scanner in = new Scanner(System.in);
            BookService bookService = new BookService(new BookDaoImpl(dataSource));

            System.out.println("""
                     Commands:
                    - all - show all books;
                    - countall - count All books;
                    - get {ID} - detailed information about the book;
                    - add - add book (autogenerated ID);
                    - update {ID} - update book information (by Id);
                    - delete {ID} - delete book;
                    - getisbn{ISBN} - get Book by ISBN;
                    - getauthor{aythor} - gety book(s) by author;
                    - sumbyauthor - sum book price by author;
                    - exit - exit;
                     Enter the command:
                    """);


            boolean exit = true;
            while (exit) {
                String commandConsole = in.nextLine();
                String[] commandConsoleArr = commandConsole.split(" ");
                commandConsole = commandConsoleArr[0].toLowerCase();
                switch (commandConsole) {
                    case "all":
                        List<Book> books = bookService.getAllBooks();
                        System.out.println("AllBooks (abbreviated representation): ");
                        for (int i = 0; i < books.size(); i++) {
                            System.out.printf("ID: %d, BookName: %s, NameAuthor: %s, DataPurchase: %td-%<tm-%<tyy %n",
                                    books.get(i).getId(), books.get(i).getTitle(), books.get(i).getNameAuthor(),
                                    books.get(i).getDateReleaseBook());
                        }
                        break;
                    case "get":
                        if (bookService.getBookById(Long.parseLong(commandConsoleArr[1])) != null) {
                            System.out.println("Book by number ".concat(commandConsoleArr[1]).concat(" :")
                                    .concat(bookService.getBookById(Long.parseLong(commandConsoleArr[1])).toString()));
                        } else {
                            System.out.println("Book not found.");
                        }
                        break;
                    case "add":
                        System.out.println("New Book: ");
                        System.out.println(bookService.createBook(addBookKeyBoard(in)));
                        break;
                    case "update":
                        if (bookService.getBookById(Long.parseLong(commandConsoleArr[1])) != null) {
                            Book book = addBookKeyBoard(in);
                            book.setId(Long.parseLong(commandConsoleArr[1]));
                            System.out.println(bookService.updateBookById(book));
                        } else {
                            System.out.println("Book not found.");
                        }
                        break;
                    case "delete":
                        if (bookService.deleteBookById(Long.parseLong(commandConsoleArr[1]))) {
                            System.out.println("Delete true");
                        } else {
                            System.out.println("Delete false");
                        }
                        break;
                    case "getisbn":
                        System.out.println("Book by isbn ".concat(commandConsoleArr[1]).concat(" :")
                                .concat(bookService.getBookByISBN(commandConsoleArr[1]).toString()));
                        break;
                    case "getauthor":
                        books = bookService.getBookByAuthor(commandConsoleArr[1]);
                        if (books != null) {
                            books.forEach(System.out::println);
                        } else {
                            System.out.println("Not Found");
                        }
                        break;
                    case "countall":
                        System.out.println("Count All books: ");
                        System.out.println(bookService.countAllBooks());
                        break;
                    case "sumbyauthor":
                        System.out.println(bookService.sumBooksByAuthor(commandConsoleArr[1]));
                        break;
                    case "exit":
                        exit = false;
                        break;
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        } finally {

            System.out.println("Thank you! GoodBye!");
        }
    }

    private Book addBookKeyBoard(Scanner in) {
        Book book = new Book();
        System.out.println("Write Book Title:");
        book.setTitle(in.nextLine());
        System.out.println("Write Book NameAuthor:");
        book.setNameAuthor(in.nextLine());
        System.out.println("Write Book DatePurchase (yyyy-MM-dd format):");
        book.setDateReleaseBook(LocalDate.of(in.nextInt(), in.nextInt(), in.nextInt()));
        in.nextLine();
        System.out.println("Write BookStore status (IN_STOCK, SOLD, RESERVE, DELIVERY_EXPECTED, OUT_OF_STOCK):");
        String statusStr = in.nextLine();
        while(!isValidStatus(statusStr)){
                System.out.println("Status not find");
            statusStr = in.nextLine();
        }
        book.setStatus(StatusBook.valueOf(statusStr));
        System.out.println("Write BookStore price: ");
        book.setPrice(in.nextBigDecimal());
        in.nextLine();
        System.out.println("Write BookStore ISBN: ");
        book.setIsbn(in.nextLine());
        return book;
    }
    public boolean isValidStatus(String status) {
        return Arrays.stream(StatusBook.values())
                .anyMatch(e -> e.toString().equals(status));
    }
}


