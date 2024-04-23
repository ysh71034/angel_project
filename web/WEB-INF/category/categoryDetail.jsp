<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-23
  Time: 오후 3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/category/category.css">
</head>
<body>
<div id="tab">
    <ul>
        <li><a href="#">도서</a></li>
        <li><a href="#">가구/장비</a></li>
        <li><a href="#">학습준비물</a></li>
        <li><a href="#">행사용품</a></li>
        <li><a href="#">기타</a></li>
    </ul>
</div>

<div id="prodList">
    <label>상품 목록</label>
    <ul>
        <c:forEach var="i" begin="1" end="16" step="1">
            <li><c:out value="${i}"/></li>
        </c:forEach>
    </ul>
</div>

</body>
</html>
