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
<body>
<div>
    <a href=""><img src="img/logo.jpg" alt="logo"><span class="home">천사Mall</span></a>
    <a href="register.do"><span class="sell">판매하기</span></a>
    <c:choose>
        <c:when test="${empty sessionScope.id}">
            <a href="login.do"><span class="login">로그인</span></a>
        </c:when>
        <c:otherwise>
            <a href="logout.do"><span class="login">로그아웃</span></a>
            <a href="detail.do"><span class="mypage">마이페이지</span></a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
