<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>BookStore</title>
</head>
<body>
<h3>AllUsers (abbreviated representation): </h3>
<hr>
<table>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Author</th>
        <th>DataPurchase</th>
    </tr>
    <c:forEach items="${requestScope.users}" var="user" varStatus="counter">
        <tr><td>${counter.count}</td>
            <td><a href="controller?command=user&id=${user.id}">${user.name} ${user.last_name}</a></td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>${user.role}</td>
        </tr>
    </c:forEach>
</table>
<form align="left" action="controller" method="get">
    <h3>What do you want to work with?(users, books):</h3>
    <p><input type="text" name="command" placeholder="write command">
        <input type="submit" value="Submit"/></p>
</form>
</body>
</html>
