<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>BookStoreError</title>
    <link rel="stylesheet" href="CSS/style.css">
</head>
<body>
<img height="150px" src="CSS/404.png" alt="404 Page not found...">
<h2><c:out value="${errorMessage}" /></h2>
<a href="#" onclick="history.back();return false;" class="history-back"><-Back</a>
</body>
</html>
