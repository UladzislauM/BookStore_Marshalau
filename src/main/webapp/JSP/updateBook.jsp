<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Book in BookStore</title>
    <link rel="stylesheet" href="CSS/style.css">
</head>
<body>
<h2 align="center">Book: ${requestScope.books.title}</h2>

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
        <form id="form1" action="controller" method="post">
            <input type="hidden" name="command" value="book_update"/>
            <input type="hidden" name="id" value="${requestScope.book.id}"/>
            <td>
                <input form="form1" type="text" value="${requestScope.book.nameAuthor}" name="name_author">
            </td>
            <td class="center">
                <input form="form1" type="date" value="${requestScope.book.dateReleaseBook}" name="data_purchase">
            </td>
            <td class="center">
                <input form="form1" type="text" value="${requestScope.book.price}" name="price">
            </td>
            <td class="center">
                <input form="form1" type="text" value="${requestScope.book.isbn}" name="isbn">
            </td>
            <td class="center">${requestScope.book.status}
                <select form="form1" name="status_book" size="1">
                    <option selected value="IN_STOCK">IN_STOCK</option>
                    <option selected value="SOLD">SOLD</option>
                    <option selected value="RESERVE">RESERVE</option>
                    <option selected value="RESERVE">DELIVERY_EXPECTED</option>
                    <option selected value="RESERVE">OUT_OF_STOCK</option>
                </select>
            </td>
        </form>
    </tr>
    </tbody>
</table>
<p><input form="form1" type="submit" name="submit" value="Update Book"/></p>

<form action="controller" method="post">
    <input type="hidden" name="command" value="book_delete"/>
    <input type="hidden" name="id" value="${requestScope.books.id}"/>
    <input type="submit" name="delete" value="Delete books"/></form>

<a href="#" onclick="history.back();return false;" class="history-back"><-Back</a>
</body>
</html>
