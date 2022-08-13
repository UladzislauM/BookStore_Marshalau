<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>BookStore</title>
  </head>
  <body>
    <h3>AllBooks (abbreviated representation): </h3>
    <hr>
    <table>
      <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Author</th>
        <th>DataPurchase</th>
      </tr>
      <c:forEach items="${requestScope.books}" var="user" varStatus="counter">
      <tr><td>${counter.count}</td>
      <td><a href="controller?command=book&id=${user.id}">${user.title}</a></td>
      <td>${user.nameAuthor}</td>
      <td>${user.dateReleaseBook}</td>
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
