<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View books in BookStore</title>
    <link rel="stylesheet" href="CSS/style.css">
</head>
<body>
<h2 align="center">Book: ${requestScope.book.title}</h2>
<table class="table">
    <thead>
    <tr>
        <th>Author:</th>
        <th>Date release books:</th>
        <th>Price:</th>
        <th>ISBN:</th>
        <th>Cover:</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            ${requestScope.book.nameAuthor}
        </td>
        <td class="center">
            ${requestScope.book.dateReleaseBook}"
        </td>
        <td class="center">
            ${requestScope.book.price}
        </td>
        <td class="center">
            ${requestScope.book.isbn}
        </td>
        <td class="center">
            ${requestScope.book.status}
        </td>
    </tr>
    </tbody>
</table>
<form action="controller" method="post">
    <input type="hidden" name="command" value="book_update_form"/>
    <input type="hidden" name="id" value="${requestScope.book.id}"/>
    <input type="submit" name="submit" value="Update Book"/></form>

<form action="controller" method="post">
    <input type="hidden" name="command" value="book_delete"/>
    <input type="hidden" name="id" value="${requestScope.book.id}"/>
    <input type="submit" name="delete" value="Delete books"/></form>

<a href="controller?command=books_find"><-Back</a>
</body>
</html>
