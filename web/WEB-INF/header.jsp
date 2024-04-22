<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-22
  Time: 오후 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href=""><img src="img/logo.jpg" alt="logo"><span>천사Mall</span></a>
<a href="register.do">판매하기</a>
<c:choose>
    <c:when test="${empty sessionScope.id}">
        <a href="login.do">로그인</a>
    </c:when>
    <c:otherwise>
        <a href="logout.do">로그아웃</a>
        <a href="detail.do">마이페이지</a>
    </c:otherwise>
</c:choose>
</body>
</html>
