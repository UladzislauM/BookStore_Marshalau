<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Books in BookStore</title>
    <link rel="stylesheet" href="CSS/style.css">
</head>
<body>
<h3 align="center">AllBooks (abbreviated representation): </h3>

<form align="left" action="controller" method="post">
    <details>
        <summary>Create New Book</summary>
        <p>Write Parameters:</p>
        <input type="hidden" name="command" value="book_create"/>
        <p><input type="text" name="title" placeholder="write title">
        <p><input type="text" name="name_author" placeholder="write author">
        <p>(Format data: yyyy-MM-dd)</p>
        <p><input type="date" name="data_purchase" placeholder="write data purchase">
        <p><input type="text" name="price" placeholder="write price">
        <p><input type="text" name="isbn" placeholder="write isbn">
        <p><input type="radio" name="status_book" value="IN_STOCK">IN_STOCK
            <input type="radio" name="status_book" value="SOLD">SOLD
            <input type="radio" name="status_book" value="RESERVE">RESERVE
            <input type="radio" name="status_book" value="DELIVERY_EXPECTED">DELIVERY_EXPECTED
            <input type="radio" name="status_book" value="OUT_OF_STOCK">OUT_OF_STOCK
        <p><input type="submit" name="submit" value="Create Book"/></p>
    </details>
</form>

<p align="left">All counts: ${requestScope.book_count}</p>

<form action="controller" method="post">
    <input type="hidden" name="command" value="book_delete"/>
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Author</th>
            <th>DataPurchase</th>
            <th>Price</th>
            <th>Cover</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.books}" var="books" varStatus="counter">
            <tr>
                <td class="center"><input type="radio" name="id" value="${books.id}">${counter.count}</td>
                <td><a href="controller?command=find_book_by_id&id=${books.id}">${books.title}</a></td>
                <td>${books.nameAuthor}</td>
                <td class="center">${books.dateReleaseBook}</td>
                <td class="center">${books.price}</td>
                <td class="center">${books.status}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" name="id" value="Delete Book"/>
</form>

<h3>What do you want to work with?(users, books):</h3>
<form action="controller" method="post">
    <input type="hidden" name="command" value="users_find"/>
    <input type="submit" value="All users"/></form>
<form action="controller" method="post">
    <input type="hidden" name="command" value="books_find"/>
    <input type="submit" value="All books"/></form>
</body>
</html>
