<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>BookStore</title>
    <link rel="stylesheet" href="./CSS/style.css">
</head>
<body>
<h3>AllUsers (abbreviated representation): </h3>
<form align="left" action="controller?post_user=user_create" method="post">
    <details>
        <summary>Create New User</summary>
        <p>Write Parameters:</p>
        <p><input type="text" name="name" placeholder="write Name">
        <p><input type="text" name="last_name" placeholder="write Last_Name">
        <p><input type="text" name="email" placeholder="write Email">
        <p><input type="text" name="password" placeholder="write Password">
        <p><input type="text" name="role" placeholder="write Role">
            <input type="submit" name="submit" value="CreateUser"/></p>
    </details>
</form>
<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Role</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.users}" var="user" varStatus="counter">
        <tr>
            <td class="center">${counter.count}</td>
            <td><a href="controller?command=user&id=${user.id}">${user.name} ${user.last_name}</a></td>
            <td>${user.email}</td>
            <td class="center">${user.role}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form align="left" action="controller" method="get">
    <h3>What do you want to work with?(users, books):</h3>
    <p><input type="text" name="command" placeholder="write command">
        <input type="submit" value="Submit"/></p>
</form>
</body>
</html>
