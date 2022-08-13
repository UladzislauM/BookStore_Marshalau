<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookStore</title>
</head>
<body>
<h2>${requestScope.book.title}</h2>
<hr>
<p>* Author: ${requestScope.book.nameAuthor}</p>
<p>* Date release book: ${requestScope.book.dateReleaseBook}</p>
<p>* Price: ${requestScope.book.price}</p>
<p>* ISBN: ${requestScope.book.isbn}</p>
<p>* Status: ${requestScope.book.status}</p>
<a href="#" onclick="history.back();return false;" class="history-back"><-Back</a>
</body>
</html>
