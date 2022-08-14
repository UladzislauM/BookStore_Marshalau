<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookStore</title>
    <link rel="stylesheet" href="./CSS/style.css">
</head>
<body>
<h3>User: ${requestScope.user.name} ${requestScope.user.last_name}</h3>
<table class="table">
    <thead>
    <tr>
        <th>Email</th>
        <th>Password</th>
        <th>Role</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${requestScope.user.email}</td>
        <td>${requestScope.user.password}</td>
        <td class="center">${requestScope.user.role}</td>
    </tr>
    </tbody>
</table>
<a href="#" onclick="history.back();return false;" class="history-back"><-Back</a>
</body>
</html>
