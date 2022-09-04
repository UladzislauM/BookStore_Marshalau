<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user in BookStore</title>
    <link rel="stylesheet" href="CSS/style.css">
</head>
<body>
<h3>User: ${requestScope.user.name}</h3>

<table class="table">
    <thead>
    <tr>
        <th>Last Name</th>
        <th>Email</th>
        <th>Password</th>
        <th>Role</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <form id="form1" action="controller" method="post">
            <input type="hidden" name="command" value="user_update"/>
            <input type="hidden" name="id" value="${requestScope.user.id}"/>
            <td>
                <input form="form1" type="text" value="${requestScope.user.last_name}" name="last_name">
            </td>
            <td class="center">
                <input form="form1" type="email" value="${requestScope.user.email}" name="email">
            </td>
            <td class="center">
                <input form="form1" type="text" value="${requestScope.user.password}" name="password">
            </td>
            <td class="center">${requestScope.user.role}
                <select form="form1" name="role" size="1">
                    <option selected value="USER">USER</option>
                    <option selected value="ADMIN">ADMIN</option>
                    <option selected value="MANAGER">MANAGER</option>
                </select>
            </td>
        </form>
    </tr>
    </tbody>
</table>
<p><input form="form1" type="submit" name="submit" value="Update User"/>

<form action="controller" method="post">
    <input type="hidden" name="command" value="user_delete"/>
    <input type="hidden" name="id" value="${requestScope.user.id}"/>
    <input type="submit" name="delete" value="Delete user"/></form>

<a href="#" onclick="history.back();return false;" class="history-back"><-Back</a>
</body>
</html>
