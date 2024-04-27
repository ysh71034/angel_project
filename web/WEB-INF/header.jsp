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
<div id="header">
    <img src="img/logo2.jpg" alt="logo">
    <a class="home" href="main.do"><span>천사Mall</span></a>
    <c:choose>
        <c:when test="${empty sessionScope.sessionID}">
            <a class="login" href="login.do"><span>로그인</span></a>
        </c:when>
        <c:otherwise>
            <a class="sell" href="regprod.do"><span>판매하기</span></a>
            <a class="login" href="logout.do"><span>로그아웃</span></a>
            <a class="mypage" href="mypage.do"><span>마이페이지</span></a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
