<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookStore</title>
</head>
<body>
<h3>${requestScope.user.name} ${requestScope.user.last_name}</h3>
<hr>
<p>* Email: ${requestScope.user.email}</p>
<p>* Password: ${requestScope.user.password}</p>
<p>* Role: ${requestScope.user.role}</p>
<a href="#" onclick="history.back();return false;" class="history-back"><-Back</a>
</body>
</html>
