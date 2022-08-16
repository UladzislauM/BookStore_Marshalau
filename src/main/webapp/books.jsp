<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>BookStore</title>
    <link rel="stylesheet" href="./CSS/style.css">
</head>
<body>
<h3 align="center">AllBooks (abbreviated representation): </h3>
<form align="left" action="controller?post=book_create" method="post">
    <details>
        <summary>Create New Book</summary>
        <p>Write Parameters:</p>
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
<form action="controller?post=book_delete" method="post">
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Author</th>
            <th>DataPurchase</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.books}" var="book" varStatus="counter">
            <tr>
                <td class="center"><input type="radio" name="id" value="${book.id}">${counter.count}</td>
                <td><a href="controller?command=book&id=${book.id}">${book.title}</a></td>
                <td>${book.nameAuthor}</td>
                <td class="center">${book.dateReleaseBook}</td>
                <td class="center">${book.price}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" name="id" value="Delete Book"/>
</form>
<form align="left" action="controller" method="get">
    <h3>What do you want to work with?(users, books):</h3>
    <p><input type="text" name="command" placeholder="write command">
        <input type="submit" value="Submit"/></p>
</form>
</body>
</html>
