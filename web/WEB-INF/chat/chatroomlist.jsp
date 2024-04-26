<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-26
  Time: 오전 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/chat/chatroomlist.css">
</head>
<body>
<c:set var="list" value="${requestScope.list}"/>
<section class="prodinfo">
    <img src="upload/${requestScope.pImg}" alt="product_img">
    <h2>상품명 : ${requestScope.pName}</h2>
</section>
<section class="listh">
    <span>방번호</span>
    <span>채팅방 링크</span>
</section>
<section class="roomtable">
    <ul class="roomNo">
        <c:forEach var="room" items="${list}">
            <li>${room.roomNo}</li>
        </c:forEach>
    </ul>
    <ul class="roompath">
        <c:forEach var="room" items="${list}">
            <li><a href="enterchat.do?productNo=${room.productNo}&buyerNo=${room.buyerNo}">${room.buyerID} 님과의 채팅방</a></li>
        </c:forEach>
    </ul>
</section>
</body>
</html>
