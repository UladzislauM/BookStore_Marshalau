<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View user in BookStore</title>
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
        <td>
            ${requestScope.user.last_name}
        </td>
        <td class="center">
            ${requestScope.user.email}
        </td>
        <td class="center">
            ${requestScope.user.password}
        </td>
        <td class="center">
            ${requestScope.user.role}
        </td>
    </tr>
    </tbody>
</table>
<form action="controller" method="post">
    <input type="hidden" name="command" value="user_update_form"/>
    <input type="hidden" name="id" value="${requestScope.user.id}"/>
    <input type="submit" name="submit" value="Update User"/></form>

<form action="controller" method="post">
    <input type="hidden" name="command" value="user_delete"/>
    <input type="hidden" name="id" value="${requestScope.user.id}"/>
    <input type="submit" name="delete" value="Delete user"/></form>

<a href="controller?command=users_find"><-Back</a>
</body>
</html>
