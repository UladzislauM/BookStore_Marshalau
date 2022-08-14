<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookStore</title>
    <link rel="stylesheet" href="./CSS/style.css">
</head>
<body>
<h2 align="center">Book: ${requestScope.book.title}</h2>
<table class="table">
    <thead>
    <tr>
        <th>Author:</th>
        <th>Date release book:</th>
        <th>Price:</th>
        <th>ISBN:</th>
        <th>Status:</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${requestScope.book.nameAuthor}</td>
        <td class="center">${requestScope.book.dateReleaseBook}</td>
        <td class="center">${requestScope.book.price}</td>
        <td class="center">${requestScope.book.isbn}</td>
        <td class="center">${requestScope.book.status}</td>
    </tr>
    </tbody>
</table>
<a href="#" onclick="history.back();return false;" class="history-back"><-Back</a>
</body>
</html>
