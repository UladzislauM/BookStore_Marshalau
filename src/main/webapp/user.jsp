<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookStore</title>
    <link rel="stylesheet" href="./CSS/style.css">
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
        <form id="form1" action="controller?post=user_update" method="post">
            <input type="hidden" name="id" value="${requestScope.user.id}"/></form>
        <td>
            <input form="form1" type="text" value="${requestScope.user.last_name}" name="last_name">
        </td>
        <td>
            <input form="form1" type="email" value="${requestScope.user.email}" name="email">
        </td>
        <td>
            <input form="form1" type="text" value="${requestScope.user.password}" name="password">
        </td>
        <td class="center">${requestScope.user.role}
            <select form="form1" name="role" size="1">
                <option selected value="USER">USER</option>
                <option selected value="ADMIN">ADMIN</option>
                <option selected value="MANAGER">MANAGER</option>
            </select>
        </td>
    </tr>
    </tbody>
</table>
<p><input form="form1" type="submit" name="submit" value="Update User"/>
<form id="form2" action="controller?post=user_delete&id=${requestScope.user.id}" method="post"></form>
<input form="form2" type="submit" name="delete" value="Delete user"/>
</p>
<a href="#" onclick="history.back();return false;" class="history-back"><-Back</a>
</body>
</html>
