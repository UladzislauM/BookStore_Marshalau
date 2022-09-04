<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Users in BookStore</title>
    <link rel="stylesheet" href="CSS/style.css">
</head>
<body>
<h3>AllUsers (abbreviated representation): </h3>

<form align="left" action="controller" method="post">
    <details>
        <summary>Create New User</summary>
        <p>Write Parameters:</p>
        <input type="hidden" name="command" value="user_create"/>
        <p><input type="text" name="name" placeholder="write Name">
        <p><input type="text" name="last_name" placeholder="write Last_Name">
        <p><input type="email" name="email" placeholder="write Email">
        <p><input type="text" name="password" placeholder="write Password">
        <p><input type="radio" name="role" value="USER">USER
            <input type="radio" name="role" value="ADMIN">ADMIN
            <input type="radio" name="role" value="MANAGER">MANAGER
        <p><input type="submit" name="submit" value="Create User"/></p>
    </details>
</form>

<p align="left">All counts: ${requestScope.user_count}</p>

<form action="controller" method="post">
    <input type="hidden" name="command" value="user_delete"/>
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
                <td class="center"><input type="radio" name="id" value="${user.id}">${counter.count}</td>
                <td><a href="controller?command=find_user_by_id&id=${user.id}">${user.name} ${user.last_name}</a></td>
                <td>${user.email}</td>
                <td class="center">${user.role}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" name="id" value="Delete User"/>
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
